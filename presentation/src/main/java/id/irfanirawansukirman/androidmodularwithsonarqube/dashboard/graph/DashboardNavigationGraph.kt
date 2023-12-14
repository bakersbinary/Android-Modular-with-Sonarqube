package id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen.DashboardScreen

@Composable
fun DashboardNavigationGraph(navHostController: NavHostController) {
  NavHost(
    navController = navHostController,
    route = Graph.ROOT,
    startDestination = Graph.DASHBOARD
  ) {
    composable(route = Graph.DASHBOARD) { DashboardScreen() }
  }
}