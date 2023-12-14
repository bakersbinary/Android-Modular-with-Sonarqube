import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.graph.Graph
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen.HomeScreen
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen.InfoScreen
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen.ScheduleScreen
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen.ShopScreen

@Composable
fun HomeNavigationGraph(navHostController: NavHostController) {
  NavHost(
    navController = navHostController,
    route = Graph.HOME,
    startDestination = DashboardNavigationItemMenu.Home.route
  ) {
    composable(route = DashboardNavigationItemMenu.Home.route) { HomeScreen() }
    composable(route = DashboardNavigationItemMenu.Schedule.route) { ScheduleScreen() }
    composable(route = DashboardNavigationItemMenu.Shop.route) { ShopScreen() }
    composable(route = DashboardNavigationItemMenu.Info.route) { InfoScreen() }
  }
}

sealed class DashboardNavigationItemMenu(
  val route: String,
  val title: String,
  val enableIcon: ImageVector,
  val disableIcon: ImageVector
) {
  data object Home : DashboardNavigationItemMenu(
    route = "Beranda",
    title = "Beranda",
    enableIcon = Icons.Filled.Home,
    disableIcon = Icons.Outlined.Home
  )

  data object Schedule : DashboardNavigationItemMenu(
    route = "Kajian",
    title = "Kajian",
    enableIcon = Icons.Filled.DateRange,
    disableIcon = Icons.Outlined.DateRange
  )

  data object Shop : DashboardNavigationItemMenu(
    route = "Belanja",
    title = "Belanja",
    enableIcon = Icons.Filled.ShoppingCart,
    disableIcon = Icons.Outlined.ShoppingCart
  )

  data object Info : DashboardNavigationItemMenu(
    route = "Info",
    title = "Info",
    enableIcon = Icons.Filled.Info,
    disableIcon = Icons.Outlined.Info
  )
}