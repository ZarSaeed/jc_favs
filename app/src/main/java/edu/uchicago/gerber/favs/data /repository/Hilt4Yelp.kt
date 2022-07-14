package edu.uchicago.gerber.favs.data.repository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.repository.YelpApi
import edu.uchicago.gerber.favs.data.repository.YelpRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Hilt4Yelp {
    @Provides
    @Singleton
    fun provideYelpApi(): YelpApi {
        return Retrofit.Builder()
            .baseUrl(Constants.apiBaseURL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YelpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api: YelpApi): YelpRepository {
        return YelpRepository(api)
    }

    private fun getOkHttpClient() = OkHttpClient.Builder().addInterceptor(getLoggingIntercepter()).build()

    //todo set HttpLoggingInterceptor.Level.BODY to HttpLoggingInterceptor.Level.NONE for production release
    private fun getLoggingIntercepter() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)




}