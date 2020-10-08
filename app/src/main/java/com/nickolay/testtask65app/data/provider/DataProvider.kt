package com.nickolay.testtask65app.data.provider

import com.nickolay.testtask65app.data.model.DatasResult
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {
    fun getInternetData(): ReceiveChannel<DatasResult>
}