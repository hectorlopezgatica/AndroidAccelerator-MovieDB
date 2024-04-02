package com.hlopezg.presentation_common.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_MOVIES = "movies"
private const val ROUTE_MOVIE = "movie/%s"
private const val ROUTE_TVS = "tvs"
private const val ROUTE_TV = "tv/%s"
private const val ARG_MOVIE_ID = "movieId"
private const val ARG_TV_ID = "tvId"
sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object Movies: NavRoutes(ROUTE_MOVIES)

    object Movie: NavRoutes(
        route = String.format(ROUTE_MOVIE, "{$ARG_MOVIE_ID}"),
        arguments = listOf(navArgument(ARG_MOVIE_ID) {
            type = NavType.LongType
        })
    ){
        fun routeForMovie(movieInput: MovieInput) = String.format(ROUTE_MOVIE, movieInput.movieId)
        fun fromEntry(entry: NavBackStackEntry): MovieInput {
            return MovieInput(entry.arguments?.getLong(ARG_MOVIE_ID) ?: 0L)
        }
    }

    object Tv : NavRoutes(
        route = String.format(ROUTE_TV, "{$ARG_TV_ID}"),
        arguments = listOf(navArgument(ARG_TV_ID) {
            type = NavType.LongType
        }
        )
    ){
        fun routeForTv(tvInput: TvInput) = String.format(ROUTE_TV, tvInput.tvId)
        fun fromEntry(entry: NavBackStackEntry): TvInput {
            return TvInput(entry.arguments?.getLong(ARG_TV_ID) ?: 0L)
        }
    }
}