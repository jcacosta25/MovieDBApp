package io.jcal.theMovie.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import io.jcal.theMovie.utils.BottomNavigationBar
import io.jcal.theMovie.utils.Navigation
import io.jcal.theMovie.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
	val navController = rememberNavController()
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		topBar = { TopBar() },
		content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
			Box(modifier = Modifier.padding(padding)) {
				Navigation(navController = navController)
			}
		},
		bottomBar = { BottomNavigationBar(navController) },
	)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
	HomeScreen()
}
