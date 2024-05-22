package com.hlopezg.data_remote.injection

import com.hlopezg.data_remote.HeaderInterceptor
import com.hlopezg.data_remote.ResponseInterceptor
import com.hlopezg.data_remote.networking.movie.MovieService
import com.hlopezg.data_remote.networking.tv.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .addNetworkInterceptor(HeaderInterceptor())
        .addInterceptor(ResponseInterceptor())
        .build()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    fun provideTvService(retrofit: Retrofit): TvService = retrofit.create(TvService::class.java)
}