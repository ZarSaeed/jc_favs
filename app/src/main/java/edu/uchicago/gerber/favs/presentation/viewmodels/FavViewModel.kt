package edu.uchicago.gerber.favs.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.models.Business
import edu.uchicago.gerber.favs.presentation.screens.search.paging.Paginate
import edu.uchicago.gerber.favs.data.repository.YelpRepository
import edu.uchicago.gerber.favs.presentation.screens.search.paging.BusinessSource
import edu.uchicago.gerber.favs.presentation.search.SearchOperation
import edu.uchicago.gerber.favs.presentation.search.SearchState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val yelpRepository: YelpRepository,
    private val application: Application
) :
    ViewModel() {

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES
    //////////////////////////////////////////
    private var _queryText = mutableStateOf<String>("")
    val queryText: State<String> = _queryText

    private var _locationText = mutableStateOf<String>("")
    val locationText: State<String> = _locationText

    private var _business = mutableStateOf<Business>(Constants.fakeBusiness)
    val business: State<Business> = _business

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    //////////////////////////////////////////
    // FUNCTIONS
    //////////////////////////////////////////
    fun setBusiness( business: Business){
        _business.value = business
    }


    fun setQueryText(query: String) {
        _queryText.value = query
    }

    fun setLocationText(location: String) {
        _locationText.value = location
    }


    fun onSearch() {
        _searchState.value = SearchState(searchOperation = SearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = SearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        BusinessSource(
                            yelpRepository = yelpRepository,
                            paginateData = Paginate(
                                query = _queryText.value,
                                location = _locationText.value
                            ),
                            application
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = SearchOperation.DONE
            )
        }
    }


}


