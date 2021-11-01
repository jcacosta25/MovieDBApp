package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	private lateinit var navController: NavController
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = setContentView(this, R.layout.activity_main)
		binding.lifecycleOwner = this
		binding.toolbar.setNavigationOnClickListener {
			onSupportNavigateUp()
		}
		setSupportActionBar(binding.toolbar)
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
		
		binding.bottomNav.setupWithNavController(navController)
	}
	
	override fun onSupportNavigateUp() = navController.navigateUp()
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean =
		item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) ||
				super.onOptionsItemSelected(item)
	
	private fun hideBottomNavigation() {
		// bottom_navigation is BottomNavigationView
		with(binding.bottomNav) {
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
		with(binding.bottomNav) {
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
