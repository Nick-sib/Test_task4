package com.nickolay.testtask65app.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.ui.fragments.SpecialtiesFragment


class MainActivity : AppCompatActivity() {//BaseActivity<List<SpecialtyModel>>() {



//    override val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        showFragment("ListData", SpecialtiesFragment.newInstance())


    }

    fun showFragment(backStack: String, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("ListData")
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