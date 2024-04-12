package com.hlopezg.androidacceleratormoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.hlopezg.androidacceleratormoviedb.ui.theme.AndroidAcceleratorMovieDBTheme
import com.hlopezg.presentation.discover.DiscoverListScreen
import com.hlopezg.presentation_common.navigation.NavRoutes
import com.hlopezg.presentation_movie.single.MovieScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAcceleratorMovieDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.Movies.route) {
        composable(route = NavRoutes.Movies.route) {
            //MovieListScreen(viewModel = hiltViewModel(), navController = navController)
            DiscoverListScreen(movieViewModel = hiltViewModel(), tvViewModel = hiltViewModel())
            //TvListScreen(viewModel = hiltViewModel(), navController = navController)
        }
        composable(route = NavRoutes.Movie.route) {
            //MovieScreen(viewModel = hiltViewModel(), movieInput = NavRoutes.Movie.fromEntry(it))
            MovieScreen(viewModel = hiltViewModel(),)
        }
        composable(route = NavRoutes.Tv.route) {

        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidAcceleratorMovieDBTheme {
        Greeting("Android")
    }
}*/
