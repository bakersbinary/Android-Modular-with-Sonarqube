package id.irfanirawansukirman.data.source.remote

import id.irfanirawansukirman.response.NowPlayingDates
import id.irfanirawansukirman.response.NowPlayingResponse
import id.irfanirawansukirman.data.service.MovieService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
class MovieRemoteSourceImplTest {

  @MockK
  lateinit var movieService: MovieService

  private lateinit var movieRemoteSourceImpl: MovieRemoteSourceImpl

  private val headerMap = hashMapOf(
    "Accept" to "application/json",
    "Authorization" to "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNjI0MWZlN2FlNzI4NTlhZDczYmZkYzhlZGUxNDM2NSIsInN1YiI6IjU3MjQ0ZjRhYzNhMzY4NDAwNDAwMWNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fofQtGWmWtToxph9Idl3N1NHlcWw0Vm6gvXvo7QXKhA"
  )

  @Before
  fun setup() {
    MockKAnnotations.init(this)

    movieRemoteSourceImpl = MovieRemoteSourceImpl(movieService)
  }

  @Test
  fun `getNowPlayingMovies is success and return response`() = runBlocking {
    val nowPlayingDates = NowPlayingDates("", "")
    val nowPlayingResponse = NowPlayingResponse(
      dates = nowPlayingDates,
      page = 1,
      results = listOf(),
      totalPages = 1,
      totalResults = 1
    )
    val response = Response.success(nowPlayingResponse)
    coEvery { movieService.getNowPlayingMovies(headerMap) } returns response

    val result = movieRemoteSourceImpl.getNowPlayingMovies()

    assertNotNull(result)
    assertEquals(result, nowPlayingResponse)
  }

  @Test(expected = Exception::class)
  fun `getNowPlayingMovies is failed and throw Exception`(): Unit = runBlocking {
    val responseBody = "{\"errors\":[\"page must be less than or equal to 500\"],\"success\":false}"
      .toResponseBody()
    val response = Response.error<NowPlayingResponse>(422, responseBody)
    coEvery { movieService.getNowPlayingMovies(headerMap) } returns response

    movieRemoteSourceImpl.getNowPlayingMovies()
  }
}