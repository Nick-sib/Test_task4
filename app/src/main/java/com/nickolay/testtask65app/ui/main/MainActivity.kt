package com.nickolay.testtask65app.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.ui.fragments.SpecialtiesFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi


class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        showFragment("ListData", SpecialtiesFragment.newInstance())


    }

    fun showFragment(backStack: String, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(backStack)
            .replace(
                R.id.fragmentContainer,
                fragment)
            .commit()
    }

    companion object {
        lateinit var instance : MainActivity
            private set
    }
}