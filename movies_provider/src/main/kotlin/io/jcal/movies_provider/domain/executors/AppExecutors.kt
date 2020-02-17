package io.jcal.movies_provider.domain.executors

import android.os.Handler
import android.os.Looper
import java.util.concurrent.*
import javax.inject.Inject

const val THREAD_COUNT = 3

/**
 * Global executor pools for the whole application.
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
open class AppExecutors @Inject constructor() {

    private val diskIO: Executor
    private val networkIO: Executor
    private val mainThread: Executor

    init {
        diskIO = DiskIOThreadExecutor()
        networkIO = Executors.newFixedThreadPool(THREAD_COUNT)
        mainThread = MainThreadExecutor()
    }

    fun getDiskThread(): Executor = diskIO

    fun getNetworkThread(): Executor = networkIO

    fun getMainThread(): Executor = mainThread
}

class MainThreadExecutor @Inject constructor() : Executor {
    //    private val mainThreadHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
        Handler(Looper.getMainLooper()).post(command)
    }
}

class DiskIOThreadExecutor @Inject constructor() : Executor {
    //    private val diskIO = Executors.newSingleThreadExecutor()
    override fun execute(command: Runnable) {
        Executors.newSingleThreadExecutor().execute(command)
    }
}
