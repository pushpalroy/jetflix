package com.fabler.jetflix.data.network.client

import com.fabler.jetflix.data.network.constant.MoviesApi
import com.fabler.jetflix.data.network.service.MoviesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApiClient {

  fun createHttpClient(): OkHttpClient {
    val requestInterceptor = Interceptor { chain ->
      val request = chain.request()
        .newBuilder()
        .build()
      return@Interceptor chain.proceed(request)
    }
    val httpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor)
    return httpClient.build()
  }

  fun createMoviesService(
    client: OkHttpClient
  ): MoviesService {
    return Retrofit.Builder()
      .client(client)
      .baseUrl(MoviesApi.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(MoviesService::class.java)
  }
}