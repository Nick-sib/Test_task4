package com.nickolay.testtask65app

import android.app.Application
import com.nickolay.testtask65app.data.DataProviderImpl
import com.nickolay.testtask65app.data.provider.DataProvider
import com.nickolay.testtask65app.data.roomdb.DbRoom

class App: Application() {

    val dataProvider: DataProvider by lazy {
        DataProviderImpl()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }



    companion object {
        lateinit var instance : App
            private set
    }
}