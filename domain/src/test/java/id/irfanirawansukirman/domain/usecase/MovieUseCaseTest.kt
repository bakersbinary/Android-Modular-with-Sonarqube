package id.irfanirawansukirman.domain.usecase

import id.irfanirawansukirman.core.exception.Failure
import id.irfanirawansukirman.core.functional.Either
import id.irfanirawansukirman.domain.repository.MovieRepository
import id.irfanirawansukirman.entity.MovieEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
class MovieUseCaseTest {

  @MockK
  lateinit var movieRepository: MovieRepository

  private lateinit var movieUseCase: MovieUseCase

  @Before
  fun setup() {
    MockKAnnotations.init(this)

    movieUseCase = MovieUseCase(movieRepository)
  }

  @Test
  fun `getNowPlayingMovies should return list of movies when repository succeeds`() = runBlocking {
    val nowPlayingMovies = listOf(MovieEntity(posterPath = ""))
    val expectedRight = Either.Right(nowPlayingMovies)
    coEvery { movieRepository.getNowPlayingMovies() } returns flowOf(expectedRight)

    val resultFlow = movieUseCase.getNowPlayingMovies()
    val resultList = resultFlow.toList()

    assertEquals(resultList, listOf(expectedRight))
  }

  @Test
  fun `getNowPlayingMovies should return failure when repository fails with RemoteFailure (RemoteException)`() =
    runBlocking {
      val failure = Failure.RemoteFailure("page must be less than or equal to 500")
      val expectedResult = Either.Left(failure)
      coEvery { movieRepository.getNowPlayingMovies() } returns flowOf(expectedResult)

      val resultFlow = movieUseCase.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      assertEquals(resultList, listOf(expectedResult))
    }

  @Test
  fun `getNowPlayingMovies should return failure when repository fails with ServerFailure (SocketException)`() =
    runBlocking {
      val failure = Failure.ServerFailure("Please check your internet connection!")
      val expectedResult = Either.Left(failure)
      coEvery { movieRepository.getNowPlayingMovies() } returns flowOf(expectedResult)

      val resultFlow = movieUseCase.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      assertEquals(resultList, listOf(expectedResult))
    }

  @Test
  fun `getNowPlayingMovies should return failure when repository fails with CommonFailure (Exception)`() =
    runBlocking {
      val failure = Failure.ServerFailure("There's something wrong!")
      val expectedResult = Either.Left(failure)
      coEvery { movieRepository.getNowPlayingMovies() } returns flowOf(expectedResult)

      val resultFlow = movieUseCase.getNowPlayingMovies()
      val resultList = resultFlow.toList()

      assertEquals(resultList, listOf(expectedResult))
    }
}