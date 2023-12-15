package id.irfanirawansukirman.androidmodularwithsonarqube.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import id.irfanirawansukirman.androidmodularwithsonarqube.R
import id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.graph.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
  val scale = remember { Animatable(0f) }
  LaunchedEffect(key1 = true) {
    scale.animateTo(
      targetValue = 0.65f,
      animationSpec = tween(
        durationMillis = 500,
        easing = { OvershootInterpolator(2f).getInterpolation(it) })
    )
    delay(3_000L)
    navHostController.run {
      popBackStack()
      navigate(Graph.DASHBOARD)
    }
  }
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Image(
      painter = painterResource(id = R.drawable.img_profile),
      contentDescription = "Logo",
      modifier = Modifier.scale(scale.value)
    )
  }
}