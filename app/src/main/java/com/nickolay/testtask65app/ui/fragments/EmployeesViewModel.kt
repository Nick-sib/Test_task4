package com.nickolay.testtask65app.ui.fragments

import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class EmployeesViewModel: BaseViewModel<List<EmployeesModel>>() {

    var id: Long = 0
    set(value) {
        field = value
        requestUser()
    }

    private fun requestUser() = launch {
        dataProvider.getEmployeesById(id).also {
            setData(it)
        }
    }


}