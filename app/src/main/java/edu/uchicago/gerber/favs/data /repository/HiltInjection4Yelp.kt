package edu.uchicago.gerber.favs.data.repository


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.repository.YelpApi
import edu.uchicago.gerber.favs.data.repository.YelpRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltInjection4Yelp {
    @Provides
    @Singleton
    fun provideYelpApi(): YelpApi {
        return Retrofit.Builder()
            .baseUrl(Constants.apiBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YelpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api: YelpApi): YelpRepository {
        return YelpRepository(api)
    }



}