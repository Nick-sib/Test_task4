package com.nickolay.testtask65app.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T>: AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    abstract val viewModel: BaseViewModel<T>
    abstract val layoutRes: Int?

    private lateinit var dataSpecialty: Job

    private lateinit var errorJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes ?.let {
            setContentView(it)
        }
    }

    override fun onStart() {
        super.onStart()
        dataSpecialty = launch {
            viewModel.getData().consumeEach {
                renderSpeciality(it)
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


    abstract fun renderSpeciality(data: T)

    private fun renderError(error: Throwable){
        when (error) {
            //is NoAuthException -> startLogin()
            else ->  error.message ?.let {
                showError(it)
            }
        }
    }

//    private fun startLogin() {
//        val providers = listOf(
//            AuthUI.IdpConfig.GoogleBuilder().build()
//        )
//
//        val intent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setLogo(R.drawable.android_robot)
//            .setTheme(R.style.LoginStyle)
//            .setAvailableProviders(providers)
//            .build()
//
//        startActivityForResult(intent, RC_SIGN_IN)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val RC_SIGN_IN = 4242
    }
}