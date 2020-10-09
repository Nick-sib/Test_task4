package com.nickolay.testtask65app.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel <T>: ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + Job()
    }


    private val specialtysChannel = BroadcastChannel<T>(Channel.CONFLATED)

    private val errorChannel = Channel<Throwable>()

    fun getSpecialtys(): ReceiveChannel<T> = specialtysChannel.openSubscription()

    fun getErrorChannel(): ReceiveChannel<Throwable> = errorChannel



    protected fun setSpecialtys(data: T) = launch {
        specialtysChannel.send(data)
    }

    protected fun setError(e: Throwable) = launch {
        errorChannel.send(e)
    }


    override fun onCleared() {
        specialtysChannel.close()

        errorChannel.close()
        coroutineContext.cancel()
        super.onCleared()
    }
}