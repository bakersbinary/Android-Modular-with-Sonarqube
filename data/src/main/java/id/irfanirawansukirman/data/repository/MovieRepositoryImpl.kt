package id.irfanirawansukirman.data.repository

import id.irfanirawansukirman.core.exception.Failure
import id.irfanirawansukirman.core.exception.Failure.CommonFailure
import id.irfanirawansukirman.core.exception.Failure.RemoteFailure
import id.irfanirawansukirman.core.exception.Failure.ServerFailure
import id.irfanirawansukirman.core.exception.RemoteException
import id.irfanirawansukirman.core.functional.Either
import id.irfanirawansukirman.core.functional.Either.Left
import id.irfanirawansukirman.core.functional.Either.Right
import id.irfanirawansukirman.data.source.remote.MovieRemoteSourceImpl
import id.irfanirawansukirman.domain.repository.MovieRepository
import id.irfanirawansukirman.entity.MovieEntity
import id.irfanirawansukirman.response.NowPlayingResults.Companion.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketException
import javax.inject.Inject

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
class MovieRepositoryImpl @Inject constructor(
  private val movieRemoteSourceImpl: MovieRemoteSourceImpl
) : MovieRepository {

  override suspend fun getNowPlayingMovies(): Flow<Either<Failure, List<MovieEntity>>> {
    return flow {
      try {
        val response = movieRemoteSourceImpl.getNowPlayingMovies()
        val result = response?.results?.map { it.toEntity() }?.toList() ?: emptyList()
        emit(Right(result))
      } catch (e: RemoteException) {
        emit(Left(RemoteFailure(e.errorModel.errors?.get(0) ?: e.errorModel.statusMessage)))
      } catch (e: SocketException) {
        emit(Left(ServerFailure("Please check your internet connection!")))
      } catch (e: Exception) {
        emit(Left(CommonFailure("There's something wrong!")))
      }
    }
  }
}