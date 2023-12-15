package id.irfanirawansukirman.androidmodularwithsonarqube.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.graph.DashboardNavigationGraph
import id.irfanirawansukirman.androidmodularwithsonarqube.theme.BaseAppTheme

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BaseAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          DashboardNavigationGraph(navHostController = rememberNavController())
        }
      }
    }
  }
}