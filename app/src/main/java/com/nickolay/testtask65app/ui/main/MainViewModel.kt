package com.nickolay.testtask65app.ui.main

import androidx.annotation.VisibleForTesting
import com.nickolay.testtask65app.data.DataAPI
import com.nickolay.testtask65app.data.entity.Employee
import com.nickolay.testtask65app.data.model.DatasResult
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask65app.dbDataFormat
import com.nickolay.testtask65app.dbNameFormat
import com.nickolay.testtask65app.dbURLFormat
import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainViewModel: BaseViewModel<List<SpecialtyModel>>() {

    //private val dataRepository = DataRepository()

    var dataModel:List<SpecialtyModel> = emptyList()

    private val internetChanel = DataAPI.loadAllData()

    init {
        launch {
            internetChanel.consumeEach {
                @Suppress("UNCHECKED_CAST")
                when (it) {
                    is DatasResult.Success<*> -> formatInternetDataToDb(it.data as List<Employee>)
                    is DatasResult.Error -> setError(it.error)
                }
            }
        }
    }

    fun formatInternetDataToDb(data: List<Employee>) {
        //Можно показать сообщение что данные загружены и сохраняются на локальном устройстве
        DataAPI.clearAllDatas()
        data.forEach {
            val employeeId =
                DataAPI.addEmployee(
                    it.f_name.dbNameFormat(),
                    it.l_name.dbNameFormat(),
                    it.birthday.dbDataFormat(),
                    it.avatr_url.dbURLFormat()
                )
            it.specialty.forEach { specialty ->
                val specialtyID = DataAPI.addSpecialty(specialty.specialty_id, specialty.name)
                DataAPI.addCrossData(employeeId, specialtyID)
            }
        }

        launch {
            val result = DataAPI.getAllSpecialties()
            if (result.isEmpty())
                setError(Throwable("EMPTY DATA"))
            else {
                dataModel = result
                setData(result)
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        internetChanel.cancel()
    }


}