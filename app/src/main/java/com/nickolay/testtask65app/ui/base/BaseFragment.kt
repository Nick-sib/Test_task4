package com.nickolay.testtask65app.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.ui.adapters.EmployeesAdapter
import com.nickolay.testtask65app.ui.fragments.EmployeesFragment
import kotlinx.android.synthetic.main.fragment_employees.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<T>: Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    abstract val viewModel: BaseViewModel<T>

    private lateinit var dataSpecialty: Job
    private lateinit var errorJob: Job


    override fun onStart() {
        super.onStart()
        dataSpecialty = launch {
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
        dataSpecialty.cancel()
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