package io.jcal.theMovie.utils

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.jcal.theMovie.R
import io.jcal.theMovie.presentation.ui.home.PopularMoviesList
import io.jcal.theMovie.presentation.ui.home.PopularShowsList
import io.jcal.theMovie.presentation.ui.movies.MovieDetailScreen

@Composable
fun TopBar() {
	TopAppBar(
		title = {
			Text(
				text = stringResource(id = R.string.app_name),
				fontSize = 18.sp,
				color = colorResource(
					id = R.color.colorOnSurface,
				),
			)
		},
		backgroundColor = colorResource(id = R.color.colorPrimary),
		contentColor = colorResource(id = R.color.colorOnSurface),
	)
}

@Preview
@Composable
fun TopBarPreview() {
	TopBar()
}

@Composable
fun BottomNavigationBar(navController: NavController) {
	val items = listOf(
		NavigationItem.PopularMovies,
		NavigationItem.PopularShows,
	)
	
	BottomNavigation(
		backgroundColor = colorResource(id = R.color.colorPrimary),
		contentColor = colorResource(id = R.color.colorOnPrimary),
	) {
		items.forEach { menuItem ->
			BottomNavigationItem(
				icon = {
					Icon(
						painterResource(id = menuItem.icon),
						contentDescription = menuItem.title,
					)
				},
				label = { Text(text = menuItem.title) },
				selectedContentColor = colorResource(id = R.color.colorOnPrimary),
				unselectedContentColor = colorResource(id = R.color.colorOnPrimary).copy(0.4f),
				alwaysShowLabel = true,
				selected = false,
				onClick = {
					navController.navigate(menuItem.route) {
						// Pop up to the start destination of the graph to
						// avoid building up a large stack of destinations
						// on the back stack as users select items
						navController.graph.startDestinationRoute?.let { route ->
							popUpTo(route) {
								saveState = true
							}
						}
						// Avoid multiple copies of the same destination when
						// reselecting the same item
						launchSingleTop = true
						// Restore state when reselecting a previously selected item
						restoreState = true
					}
				},
			)
		}
	}
}

@Composable
fun Navigation(navController: NavHostController) {
	NavHost(navController, startDestination = NavigationItem.PopularMovies.route) {
		composable(
			NavigationItem.PopularMovies.route,
			listOf(),
		) {
			PopularMoviesList({ movie ->
				navController.navigate(
					NavigationItem.MovieDetails.route.replace(
						"{movieId}",
						"${movie.id}",
					),
				)
			})
		}
		composable(NavigationItem.PopularShows.route) {
			PopularShowsList({})
		}
		composable(
			NavigationItem.MovieDetails.route,
			arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
		) { backStackEntry ->
			MovieDetailScreen(movieId = backStackEntry.arguments?.getInt("movieId"))
		}
	}
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
	object PopularMovies : NavigationItem("movies", R.drawable.ic_movie, "Movies")
	object PopularShows : NavigationItem("shows", R.drawable.ic_tv, "TV Shows")
	object MovieDetails : NavigationItem("movie/{movieId}", R.drawable.ic_movie, "Movie Detail")
	
	object ShowDetail : NavigationItem("show/{showId}", R.drawable.ic_tv, "Show Detail")
}