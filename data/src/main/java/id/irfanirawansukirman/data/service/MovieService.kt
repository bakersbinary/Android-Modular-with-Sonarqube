package id.irfanirawansukirman.data.service

import id.irfanirawansukirman.response.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

/**
 * Created by irfanirawansukirman on 18/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
interface MovieService {

  @GET("movie/now_playing?language=en-US&page=1")
  suspend fun getNowPlayingMovies(@HeaderMap headerMap: Map<String, String>): Response<NowPlayingResponse>
}