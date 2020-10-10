package com.nickolay.testtask65app.ui.main

import androidx.annotation.VisibleForTesting
import com.nickolay.testtask65app.data.DataRepository
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

    private val internetChanel = DataRepository.loadAllData()


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
        DataRepository.clearAllDatas()
        data.forEach {
            val employee_id =
                DataRepository.addEmployee(
                    it.f_name.dbNameFormat(),
                    it.l_name.dbNameFormat(),
                    it.birthday.dbDataFormat(),
                    it.avatr_url.dbURLFormat()
                )
            it.specialty.forEach { specialty ->
                val specialtyID = DataRepository.addSpecialty(specialty.specialty_id, specialty.name)
                DataRepository.addCrossData(employee_id, specialtyID)
            }
        }

        launch {
            setData(DataRepository.getAllSpecialtys())
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        internetChanel.cancel()
    }


}