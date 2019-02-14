package io.jcal.theMovie.presentation.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.jcal.theMovie.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
