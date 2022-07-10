package edu.uchicago.gerber.favs.presentation.screens.search.paging

import android.app.Application
import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.models.Business
import edu.uchicago.gerber.favs.data.repository.YelpRepository
import javax.inject.Inject

class BusinessSource @Inject constructor(
    private val yelpRepository: YelpRepository,
    private val paginateData: Paginate,
    private val application: Application
) :
    PagingSource<Int, Business>() {
    override fun getRefreshKey(state: PagingState<Int, Business>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Business> {
        return try {
            val prev = params.key ?: 0
            val response = yelpRepository.searchBusiness(
                startIndex = prev,
                maxResults = params.loadSize,
                query = paginateData.query,
                location = paginateData.location,
                key = application.resources.getString(R.string.yelp3_key)
            )

            if (response.isSuccessful) {
                val body = response.body()?.businesses;
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = if (body.size < params.loadSize) null else prev + 1
                )
            } else {
                //TODO IMPORTANT, ONCE YOU GET YOUR YELP3 API KEY, remove the following block
                //TODO and revert to LoadResult.Error(Exception())
                val body = Constants.fakeBusinessResponse.businesses
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = if (body.size < params.loadSize) null else prev + 1
                )
                // LoadResult.Error(Exception())
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }



}