package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.jcal.theMovie.presentation.ui.home.HomeScreen
import io.jcal.theMovie.presentation.ui.theme.MyApplicationTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyApplicationTheme {
				HomeScreen()
			}
		}
	}
}
