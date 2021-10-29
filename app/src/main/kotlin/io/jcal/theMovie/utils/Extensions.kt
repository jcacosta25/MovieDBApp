package io.jcal.theMovie.utils

import android.view.View
import kotlinx.coroutines.Job

fun Job?.cancelIfActive() {
	if (this?.isActive == true) {
		cancel()
	}
}

fun View.toTransitionGroup() = this to transitionName
