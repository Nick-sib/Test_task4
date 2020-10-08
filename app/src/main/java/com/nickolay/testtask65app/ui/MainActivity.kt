package com.nickolay.testtask65app.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nickolay.testtask65app.App
import com.nickolay.testtask65app.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("myLOG", "database = ${App.instance.database.getEmployeesDao()}")


    }
}