package io.jcal.theMovie.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import io.jcal.theMovie.presentation.ui.series.TvShowDetailScreen

@Composable
fun TopBar(navController: NavController, backState: MutableState<Boolean>) {
	TopAppBar(
		title = {
			Text(
				text = when (navController.currentDestination?.route) {
					NavigationItem.MovieDetails.route -> NavigationItem.MovieDetails.title
					NavigationItem.ShowDetail.route -> NavigationItem.ShowDetail.title
					NavigationItem.PopularMovies.route -> NavigationItem.PopularMovies.title
					NavigationItem.PopularShows.route -> NavigationItem.PopularShows.title
					else -> stringResource(id = R.string.app_name)
				},
				fontSize = 18.sp,
				color = colorResource(
					id = R.color.colorOnSurface,
				),
			)
		},
		backgroundColor = colorResource(id = R.color.colorPrimary),
		contentColor = colorResource(id = R.color.colorOnSurface),
		navigationIcon = if (!backState.value) {
			{
				IconButton(onClick = { navController.popBackStack() }) {
					Icon(
						imageVector = Icons.Filled.ArrowBack,
						contentDescription = "Back",
						tint = colorResource(id = R.color.colorAccent),
					)
				}
			}
		} else {
			null
		},
	)
}

@Composable
fun BottomNavigationBar(navController: NavController, bottomState: MutableState<Boolean>) {
	val items = listOf(
		NavigationItem.PopularMovies,
		NavigationItem.PopularShows,
	)
	
	AnimatedVisibility(
		visible = bottomState.value,
		enter = slideInVertically(initialOffsetY = { it }),
		exit = slideOutVertically(targetOffsetY = { it }),
		content = {
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
								tint = colorResource(id = R.color.colorOnPrimary),
							)
						},
						label = {
							Text(
								text = menuItem.title,
								color = colorResource(id = R.color.colorOnPrimary),
							)
						},
						selectedContentColor = colorResource(id = R.color.colorOnPrimary),
						unselectedContentColor = colorResource(id = R.color.colorOnPrimary).copy(
							0.4f,
						),
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
		},
	)
}

@Composable
fun Navigation(navController: NavHostController, bottomState: MutableState<Boolean>) {
	NavHost(navController, startDestination = NavigationItem.PopularMovies.route) {
		composable(
			NavigationItem.PopularMovies.route,
			listOf(),
		) {
			bottomState.value = true
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
			bottomState.value = true
			PopularShowsList({ tvShow ->
				navController.navigate(
					NavigationItem.ShowDetail.route.replace(
						"{showId}",
						"${tvShow.id}",
					),
				)
			})
		}
		composable(
			NavigationItem.MovieDetails.route,
			arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
		) { backStackEntry ->
			bottomState.value = false
			MovieDetailScreen(movieId = backStackEntry.arguments?.getInt("movieId"))
		}
		composable(
			NavigationItem.ShowDetail.route,
			arguments = listOf(navArgument("showId") { type = NavType.IntType }),
		) { backStackEntry ->
			bottomState.value = false
			TvShowDetailScreen(
				tvShowId = backStackEntry.arguments?.getInt("showId"),
			)
		}
	}
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
	object PopularMovies : NavigationItem("movies", R.drawable.ic_movie, "Movies")
	object PopularShows : NavigationItem("shows", R.drawable.ic_tv, "TV Shows")
	object MovieDetails : NavigationItem("movie/{movieId}", R.drawable.ic_movie, "Movie Detail")
	object ShowDetail : NavigationItem("show/{showId}", R.drawable.ic_tv, "Show Detail")
}