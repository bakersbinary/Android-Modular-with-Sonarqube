package id.irfanirawansukirman.data.source.remote

import id.irfanirawansukirman.response.NowPlayingResponse

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
interface MovieRemoteSource {

  suspend fun getNowPlayingMovies(): NowPlayingResponse?
}