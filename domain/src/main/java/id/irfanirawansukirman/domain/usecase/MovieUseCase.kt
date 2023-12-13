package id.irfanirawansukirman.domain.usecase

import id.irfanirawansukirman.core.exception.Failure
import id.irfanirawansukirman.core.functional.Either
import id.irfanirawansukirman.entity.MovieEntity
import id.irfanirawansukirman.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
class MovieUseCase @Inject constructor(
  private val movieRepository: MovieRepository
) {

  suspend fun getNowPlayingMovies(): Flow<Either<Failure, List<MovieEntity>>> {
    return movieRepository.getNowPlayingMovies()
  }
}