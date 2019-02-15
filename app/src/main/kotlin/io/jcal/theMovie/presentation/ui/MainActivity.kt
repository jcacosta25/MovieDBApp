package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import io.jcal.theMovie.R
import io.jcal.theMovie.databinding.ActivityMainBinding
import io.jcal.theMovie.utils.KeepStateNavigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        // hide and show bottomNavigation on detail
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detail_show_dest -> hideBottomNavigation()
                R.id.detail_movie_dest -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
        // get fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        // setup custom navigator
        val navigator =
            KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider += navigator

        // set navigation graph
        navController.setGraph(R.navigation.app_navigation)

        binding.bottomNav.setupWithNavController(navController)
    }

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
