package com.nickolay.testtask65app.ui.base


import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
abstract class BaseFragment<T>: Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }


    abstract val viewModel: BaseViewModel<T>

    private lateinit var dataJob: Job
    private lateinit var errorJob: Job


    @ObsoleteCoroutinesApi
    override fun onStart() {
        super.onStart()
        dataJob = launch {
            viewModel.getData().consumeEach {
                renderData(it)
            }
        }


        errorJob = launch{
            viewModel.getErrorChannel().consumeEach {
                renderError(it)
            }
        }
    }

    override fun onStop() {
        dataJob.cancel()
        errorJob.cancel()
        super.onStop()
    }


    abstract fun renderData(data: T)

    private fun renderError(error: Throwable){
         error.message ?.let {
             showError(it)
         }
    }


    private fun showError(message: String) {
        Log.d("myLOG", "Fragment Error = $message")
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}