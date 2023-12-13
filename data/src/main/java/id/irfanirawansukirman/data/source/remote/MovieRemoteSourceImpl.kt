package id.irfanirawansukirman.data.source.remote

import com.google.gson.Gson
import id.irfanirawansukirman.core.exception.RemoteException
import id.irfanirawansukirman.core.exception.ErrorModel
import id.irfanirawansukirman.data.service.MovieService
import id.irfanirawansukirman.response.NowPlayingResponse
import javax.inject.Inject

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
class MovieRemoteSourceImpl @Inject constructor(
  private val movieService: MovieService
) : MovieRemoteSource {

  override suspend fun getNowPlayingMovies(): NowPlayingResponse? {
    val headerMap = hashMapOf(
      "Accept" to "application/json",
      "Authorization" to "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNjI0MWZlN2FlNzI4NTlhZDczYmZkYzhlZGUxNDM2NSIsInN1YiI6IjU3MjQ0ZjRhYzNhMzY4NDAwNDAwMWNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fofQtGWmWtToxph9Idl3N1NHlcWw0Vm6gvXvo7QXKhA"
    )
    val response = movieService.getNowPlayingMovies(headerMap)
    return if (response.isSuccessful) {
      response.body()
    } else {
      val errorModel = Gson().fromJson(response.errorBody().toString(), ErrorModel::class.java)
      throw RemoteException(errorModel)
    }
  }
}