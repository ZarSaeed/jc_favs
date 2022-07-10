package edu.uchicago.gerber.favs.data.repository


import edu.uchicago.gerber.favs.data.models.BusinessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface YelpApi {
    @GET("businesses/search")
    suspend fun searchBusinesses(
        @Query("term") query: String?,
        @Query("location") location: String?,
        @Query("limit") maxResults: Int,
        @Query("offset") startIndex: Int,
        @Header("Authorization") key: String?
    ): Response<BusinessResponse>
}