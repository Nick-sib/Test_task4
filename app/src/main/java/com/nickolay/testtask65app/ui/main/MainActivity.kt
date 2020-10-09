package com.nickolay.testtask65app.ui.main


import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.DataRepository
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.SpecialtysAdapter
import com.nickolay.testtask65app.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<List<SpecialtyModel>>() {

    override val layoutRes = R.layout.activity_main

    override val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view_pager.adapter = SpecialtysAdapter(supportFragmentManager)


    }

    override fun renderSpeciality(data: List<SpecialtyModel>) {
        (view_pager.adapter as SpecialtysAdapter).specialtys = data
        Log.d("myLOG", "data = $data")

    }
}