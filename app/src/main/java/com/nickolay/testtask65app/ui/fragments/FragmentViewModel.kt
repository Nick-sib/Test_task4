package com.nickolay.testtask65app.ui.fragments

import android.util.Log
import com.nickolay.testtask65app.data.DataAPI
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FragmentViewModel: BaseViewModel<List<EmployeesModel>>() {
    //val dataRepository = DataRepository()

    var id: Long = 0
    set(value) {
        field = value
        requestUser()
    }

    private fun requestUser() = launch {
        DataAPI.getEmployeesById(id).also {
            setData(it)
        }
    }




}