package id.irfanirawansukirman.androidmodularwithsonarqube

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import id.irfanirawansukirman.core.exception.Failure
import id.irfanirawansukirman.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by irfanirawansukirman on 20/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
  private val movieUseCase: MovieUseCase
) : ViewModel() {

  suspend fun getNowPlayingMovies() {
    viewModelScope.launch {
      movieUseCase.getNowPlayingMovies()
        .collect { response ->
          response.fold({ failure ->
            when (failure) {
              is Failure.RemoteFailure -> {
                Timber.tag("MainViewModel [RemoteFailure]").e(failure.message)
              }

              is Failure.ServerFailure -> {
                Timber.tag("MainViewModel [ServerFailure]").e(failure.message)
              }

              is Failure.CommonFailure -> {
                Timber.tag("MainViewModel [CommonFailure]").e(failure.message)
              }
            }
          }, { result ->
            Timber.tag("MainViewModel [Success]").d(Gson().toJson(result))
          })
        }
    }
  }
}