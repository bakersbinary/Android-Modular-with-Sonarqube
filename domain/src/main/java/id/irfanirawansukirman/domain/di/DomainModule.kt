package id.irfanirawansukirman.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.irfanirawansukirman.domain.repository.MovieRepository
import id.irfanirawansukirman.domain.usecase.MovieUseCase

/**
 * Created by irfanirawansukirman on 20/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

  @Provides
  fun provideMovieUseCase(movieRepository: MovieRepository): MovieUseCase {
    return MovieUseCase(movieRepository)
  }
}