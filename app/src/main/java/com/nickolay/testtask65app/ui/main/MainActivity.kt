package com.nickolay.testtask65app.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.ui.fragments.SpecialtysFragment


class MainActivity : AppCompatActivity() {//BaseActivity<List<SpecialtyModel>>() {



//    override val viewModel: MainViewModel by lazy {
//        ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.fragmentContainer,
                SpecialtysFragment.newInstance())
            .commit()


//        view_pager.adapter = SpecialtysAdapter(supportFragmentManager)


    }

//    override fun renderSpeciality(data: List<SpecialtyModel>) {
//        (view_pager.adapter as SpecialtysAdapter).specialtys = data
//    }
}