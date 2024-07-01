package com.hlopezg.androidacceleratormoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.hlopezg.androidacceleratormoviedb.ui.theme.AndroidAcceleratorMovieDBTheme
import com.hlopezg.presentation.discover.DiscoverListScreen
import com.hlopezg.presentation_movie.navigate.ScreenMovieDetail
import com.hlopezg.presentation_movie.single.MovieScreenDetail
import com.hlopezg.presentation_tv.navigate.ScreenTvDetail
import com.hlopezg.presentation_tv.single.TvScreenDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAcceleratorMovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    App(
                        navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App(
    navController: NavHostController,
    modifier: Modifier,
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = ScreenDiscover) {
            composable<ScreenDiscover> {
                DiscoverListScreen(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    movieViewModel = hiltViewModel(),
                    tvViewModel = hiltViewModel(),
                    navController = navController
                )
            }
            composable<ScreenMovieDetail> {
                val args = it.toRoute<ScreenMovieDetail>()
                MovieScreenDetail(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    viewModel = hiltViewModel(),
                    commonContentDetail = args,
                )
            }
            composable<ScreenTvDetail> {
                val args = it.toRoute<ScreenTvDetail>()
                TvScreenDetail(
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    viewModel = hiltViewModel(),
                    commonContentDetail = args,
                )
            }
        }
    }
}

@Serializable
object ScreenDiscover
