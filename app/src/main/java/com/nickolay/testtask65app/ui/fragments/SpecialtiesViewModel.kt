package com.nickolay.testtask65app.ui.fragments

import androidx.annotation.VisibleForTesting
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
class SpecialtiesViewModel: BaseViewModel<List<SpecialtyModel>>() {

    private val internetChanel = dataProvider.loadAllData()

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

    private fun formatInternetDataToDb(data: List<Employee>) {
        //Можно показать сообщение что данные загружены и сохраняются на локальном устройстве
        dataProvider.clearAllDatas()
        data.forEach {
            val employeeId =
                dataProvider.addEmployee(
                    it.f_name.dbNameFormat(),
                    it.l_name.dbNameFormat(),
                    it.birthday.dbDataFormat(),
                    it.avatr_url.dbURLFormat()
                )
            it.specialty.forEach { specialty ->
                val specialtyID = dataProvider.addSpecialty(specialty.specialty_id, specialty.name)
                dataProvider.addCrossData(employeeId, specialtyID)
            }
        }

        launch {
            val result = dataProvider.getAllSpecialties()
            if (result.isEmpty())
                setError(Throwable("EMPTY DATA"))
            else {
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