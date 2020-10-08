package com.nickolay.testtask65app.data.model

sealed class DatasResult {
    class Success<out T>(val data: T): DatasResult()
    class Error(val error: Throwable): DatasResult()
}