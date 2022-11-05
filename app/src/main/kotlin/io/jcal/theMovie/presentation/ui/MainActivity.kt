package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import io.jcal.theMovie.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	
	private lateinit var navController: NavController
	private lateinit var toolbar: Toolbar
	private lateinit var bottomNav: BottomNavigationView
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		toolbar = findViewById(R.id.toolbar)
		bottomNav = findViewById(R.id.bottom_nav)
		toolbar.setNavigationOnClickListener {
			onSupportNavigateUp()
		}
		setSupportActionBar(toolbar)
		navController = findNavController(R.id.nav_host_fragment)
		
		// hide and show bottomNavigation on detail
		navController.addOnDestinationChangedListener { _, destination, _ ->
			when (destination.id) {
				R.id.movieDetailFragment -> hideBottomNavigation()
				R.id.showDetailFragment -> hideBottomNavigation()
				else -> showBottomNavigation()
			}
		}
		val appBarConfiguration = AppBarConfiguration.Builder(
			R.id.popularMoviesFragment,
			R.id.popularShowsFragment
		).build()
		setupActionBarWithNavController(navController, appBarConfiguration)
		
		bottomNav.setupWithNavController(navController)
	}
	
	override fun onSupportNavigateUp() = navController.navigateUp()
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean =
		item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) ||
				super.onOptionsItemSelected(item)
	
	private fun hideBottomNavigation() {
		// bottom_navigation is BottomNavigationView
		with(bottomNav) {
			if (visibility == View.VISIBLE && alpha == 1f) {
				animate()
					.alpha(0f)
					.withEndAction { visibility = View.GONE }
					.duration = EXIT_DURATION
			}
		}
	}
	
	private fun showBottomNavigation() {
		// bottom_navigation is BottomNavigationView
		with(bottomNav) {
			visibility = View.VISIBLE
			animate()
				.alpha(1f)
				.duration = ENTER_DURATION
		}
	}
	
	companion object {
		private const val ENTER_DURATION = 1L
		private const val EXIT_DURATION = 1L
	}
}
