package com.nickolay.testtask65app.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nickolay.testtask65app.App
import com.nickolay.testtask65app.R


class MainActivity : AppCompatActivity() {

     val viewModel = MainViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
}