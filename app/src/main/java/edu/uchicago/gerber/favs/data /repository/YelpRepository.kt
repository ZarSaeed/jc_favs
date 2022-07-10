package edu.uchicago.gerber.favs.data.repository

import android.util.Log
import edu.uchicago.gerber.favs.data.models.BusinessResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class YelpRepository @Inject constructor(private val api: YelpApi) {
    suspend fun searchBusiness(
        query: String,
        location: String,
        maxResults: Int,
        startIndex: Int,
        key: String
    ): Response<BusinessResponse> {
        return withContext(Dispatchers.IO) {
            Log.d("outgoing", " $query  $location $maxResults $startIndex $key" )
            api.searchBusinesses(
                query = query,
                location = location,
                maxResults = maxResults,
                startIndex = startIndex,
                key
            )


        }
    }
}