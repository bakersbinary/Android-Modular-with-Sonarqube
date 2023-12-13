package id.irfanirawansukirman.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.irfanirawansukirman.data.repository.MovieRepositoryImpl
import id.irfanirawansukirman.domain.repository.MovieRepository

/**
 * Created by irfanirawansukirman on 20/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}