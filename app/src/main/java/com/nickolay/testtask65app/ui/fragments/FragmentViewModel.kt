package com.nickolay.testtask65app.ui.fragments

import android.util.Log
import com.nickolay.testtask65app.data.DataRepository
import com.nickolay.testtask65app.data.entity.Employee
import com.nickolay.testtask65app.data.model.DatasResult
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class FragmentViewModel: BaseViewModel<List<EmployeesModel>>() {
    //val dataRepository = DataRepository()

    var id: Long = 0
    set(value) {
        field = value

        Log.d("myLOG", "dataRepository = $DataRepository")
        requestUser()
    }

    private fun requestUser() = launch {
        DataRepository.getEmployeesById(id).also {
            setData(it)
        }


    }




}