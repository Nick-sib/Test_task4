package com.nickolay.testtask65app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nickolay.testtask65app.data.provider.ThreadProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ThreadProvider.getAllEmployees()
    }
}