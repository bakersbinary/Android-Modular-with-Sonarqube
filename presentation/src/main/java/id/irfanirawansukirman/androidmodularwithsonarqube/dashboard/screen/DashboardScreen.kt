package id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen

import DashboardNavigationItemMenu
import HomeNavigationGraph
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navHostController: NavHostController = rememberNavController()) {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
    Scaffold(
      bottomBar = { BuildDashboardBottomNavigation(navHostController = navHostController) }
    ) {
      HomeNavigationGraph(navHostController = navHostController)
    }
  }
}

@Composable
fun BuildDashboardBottomNavigation(navHostController: NavHostController) {
  val screens = listOf(
    DashboardNavigationItemMenu.Home,
    DashboardNavigationItemMenu.Schedule,
    DashboardNavigationItemMenu.Shop,
    DashboardNavigationItemMenu.Info
  )

  val navBackStackEntry by navHostController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  val bottomBarDestination = screens.any { it.route == currentDestination?.route }
  if (bottomBarDestination) {
    BottomNavigation {
      screens.forEach { screen ->
        AddItem(
          screen = screen,
          currentDestination = currentDestination,
          navController = navHostController
        )
      }
    }
  }
}

@Composable
fun RowScope.AddItem(
  screen: DashboardNavigationItemMenu,
  currentDestination: NavDestination?,
  navController: NavHostController
) {
  BottomNavigationItem(
    label = {
      Text(text = screen.title)
    },
    icon = {
      Icon(
        imageVector = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) screen.enableIcon else screen.disableIcon,
        contentDescription = screen.title
      )
    },
    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
    unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
    onClick = {
      navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id)
        launchSingleTop = true
      }
    }
  )
}