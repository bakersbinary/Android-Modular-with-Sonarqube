package id.irfanirawansukirman.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.irfanirawansukirman.data.BuildConfig
import id.irfanirawansukirman.data.service.MovieService
import id.irfanirawansukirman.data.source.remote.MovieRemoteSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

  @Provides
  fun provideHttpLoggingInterceptor(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
      val httpLoggingInterceptor = HttpLoggingInterceptor()
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
      builder.addInterceptor(httpLoggingInterceptor)
    }

    return builder.build();
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }

  @Provides
  fun provideMovieRemoteSourceImpl(movieService: MovieService): MovieRemoteSourceImpl {
    return MovieRemoteSourceImpl(movieService)
  }
}
