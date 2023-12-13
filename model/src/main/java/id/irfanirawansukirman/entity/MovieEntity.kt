package id.irfanirawansukirman.entity

import id.irfanirawansukirman.response.NowPlayingResults

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
data class MovieEntity(
  val posterPath: String
) {
  companion object {

    fun MovieEntity.toModel(): NowPlayingResults {
      return NowPlayingResults()
    }
  }
}
