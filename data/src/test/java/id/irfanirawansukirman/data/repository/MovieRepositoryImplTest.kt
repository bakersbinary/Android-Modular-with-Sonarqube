package id.irfanirawansukirman.data.repository

import id.irfanirawansukirman.core.exception.ErrorModel
import id.irfanirawansukirman.core.exception.Failure.CommonFailure
import id.irfanirawansukirman.core.exception.Failure.RemoteFailure
import id.irfanirawansukirman.core.exception.Failure.ServerFailure
import id.irfanirawansukirman.core.exception.RemoteException
import id.irfanirawansukirman.core.functional.Either.Left
import id.irfanirawansukirman.core.functional.Either.Right
import id.irfanirawansukirman.data.source.remote.MovieRemoteSourceImpl
import id.irfanirawansukirman.entity.MovieEntity
import id.irfanirawansukirman.entity.MovieEntity.Companion.toModel
import id.irfanirawansukirman.response.NowPlayingDates
import id.irfanirawansukirman.response.NowPlayingResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.SocketException

/**
 * Created by irfanirawansukirman on 20/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
class MovieRepositoryImplTest {

  @MockK
  lateinit var movieRemoteSourceImpl: MovieRemoteSourceImpl

  private lateinit var movieRepositoryImpl: MovieRepositoryImpl

  @Before
  fun setup() {
    MockKAnnotations.init(this)

    movieRepositoryImpl = MovieRepositoryImpl(movieRemoteSourceImpl)
  }

  @Test
  fun `getNowPlayingMovies should return list of movies when remote source succeeds`() =
    runBlocking {
      val nowPlayingMovies = listOf(MovieEntity(posterPath = ""))
      val results = nowPlayingMovies.map { it.toModel() }.toList()
      val nowPlayingDates = NowPlayingDates("", "")
      val response = NowPlayingResponse(
        dates = nowPlayingDates,
        page = 1,
        results = results,
        totalPages = 1,
        totalResults = 1
      )
      coEvery { movieRemoteSourceImpl.getNowPlayingMovies() } returns response

      val resultFlow = movieRepositoryImpl.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      val expectedResult = listOf(Right(nowPlayingMovies))
      assertEquals(resultList, expectedResult)
    }

  @Test
  fun `getNowPlayingMovies should return failure when remote source throws RemoteException`() =
    runBlocking {
      val remoteException = RemoteException(ErrorModel(listOf(""), false, "", ""))
      coEvery { movieRemoteSourceImpl.getNowPlayingMovies() } throws remoteException

      val resultFlow = movieRepositoryImpl.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      val expectedOutput = listOf(
        Left(
          RemoteFailure(
            remoteException.errorModel.errors?.get(0) ?: remoteException.errorModel.statusMessage
          )
        )
      )
      assertEquals(expectedOutput, resultList)
    }

  @Test
  fun `getNowPlayingMovies should return failure when remote source throws SocketException`() =
    runBlocking {
      val socketException = SocketException("Please check your internet connection!")
      coEvery { movieRemoteSourceImpl.getNowPlayingMovies() } throws socketException

      val resultFlow = movieRepositoryImpl.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      val expectedOutput = listOf(Left(ServerFailure("Please check your internet connection!")))
      assertEquals(expectedOutput, resultList)
    }

  @Test
  fun `getNowPlayingMovies should return failure when remote source throws Exception`() =
    runBlocking {
      val exception = Exception("There's something wrong!")
      coEvery { movieRemoteSourceImpl.getNowPlayingMovies() } throws exception

      val resultFlow = movieRepositoryImpl.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      val expectedOutput = listOf(Left(CommonFailure("There's something wrong!")))
      assertEquals(expectedOutput, resultList)
    }
}