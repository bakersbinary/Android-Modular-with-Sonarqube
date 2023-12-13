package id.irfanirawansukirman.domain.repository

import id.irfanirawansukirman.core.exception.Failure
import id.irfanirawansukirman.core.functional.Either
import id.irfanirawansukirman.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
interface MovieRepository {

  suspend fun getNowPlayingMovies(): Flow<Either<Failure, List<MovieEntity>>>
}