package com.nickolay.testtask65app.ui.main


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.adapters.SpecialtiesAdapter
import com.nickolay.testtask65app.ui.base.BaseActivity
import com.nickolay.testtask65app.ui.fragments.SpecialtiesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_specialtys.view_pager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MainActivity: BaseActivity<List<SpecialtyModel>>() {

    override val viewModel: SpecialtiesViewModel by lazy {
        ViewModelProvider(this).get(SpecialtiesViewModel::class.java)
    }

    override fun renderData(data: List<SpecialtyModel>) {
        (view_pager.adapter as SpecialtiesAdapter).specialties = data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        view_pager.adapter = SpecialtiesAdapter(supportFragmentManager)
        tabs.setupWithViewPager(view_pager)

    }

//    fun showFragment(backStack: String? = null, fragment: Fragment?) {
//        fragment?.run {
//            backStack?.run {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(
//                        R.id.fragmentContainer,
//                        fragment
//                    )
//                    .commit()
//            } ?: run {
//                supportFragmentManager
//                    .beginTransaction()
//                    .addToBackStack(backStack)
//                    .replace(
//                        R.id.fragmentContainer,
//                        fragment
//                    )
//                    .commit()
//            }
//        }
//    }

    companion object {
        lateinit var instance : MainActivity
            private set
    }


}