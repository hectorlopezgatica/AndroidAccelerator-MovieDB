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
    data object Tv : NavRoutes(
        route = String.format(ROUTE_TV, "{$ARG_TV_ID}"),
        arguments = listOf(navArgument(ARG_TV_ID) {
            type = NavType.LongType
        }
        )
    ){
        fun routeForTv(tvInput: TvInput) = String.format(ROUTE_TV, tvInput.tvId)
        fun fromEntry(entry: NavBackStackEntry): TvInput {
            val id = entry.arguments?.getString(ARG_TV_ID) ?: ""
            return TvInput(id.toLong())
        }
    }
}