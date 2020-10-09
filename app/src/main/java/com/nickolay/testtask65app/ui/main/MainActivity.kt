package com.nickolay.testtask65app.ui.main


import android.util.Log
import com.nickolay.testtask65app.R
import com.nickolay.testtask65app.data.DataRepository
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.ui.base.BaseActivity



class MainActivity : BaseActivity<List<SpecialtyModel>>() {

    override val layoutRes = R.layout.activity_main

    override val viewModel: MainViewModel by lazy {
        MainViewModel()
    }


    override fun renderSpeciality(data: List<SpecialtyModel>) {
        Log.d("myLOG", "data = $data")

    }
}