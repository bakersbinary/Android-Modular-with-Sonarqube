package id.irfanirawansukirman.androidmodularwithsonarqube

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by irfanirawansukirman on 19/07/23
 * Copyright (c) 2023 PT. Intersolusi Teknologi Asia, All Rights Reserved.
 */
@HiltAndroidApp
class MainApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}