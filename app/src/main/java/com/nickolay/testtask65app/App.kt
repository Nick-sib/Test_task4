package com.nickolay.testtask65app

import android.app.Application
import androidx.fragment.app.FragmentManager
import com.nickolay.testtask65app.data.roomdb.DbRoom

class App: Application() {

    val database: DbRoom by lazy {
        DbRoom.getDatabase(this)
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