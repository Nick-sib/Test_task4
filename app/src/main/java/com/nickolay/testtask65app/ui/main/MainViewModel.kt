package com.nickolay.testtask65app.ui.main

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.nickolay.testtask65app.data.DataRepository
import com.nickolay.testtask65app.data.entity.Employee
import com.nickolay.testtask65app.data.model.DatasResult
import com.nickolay.testtask65app.dbDataFormat
import com.nickolay.testtask65app.dbNameFormat
import com.nickolay.testtask65app.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import java.text.SimpleDateFormat


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainViewModel: BaseViewModel<List<Employee>?>() {

    private val dataRepository = DataRepository()

    private val internetChanel = dataRepository.loadAllData()


    init {
        launch {
            internetChanel.consumeEach {
                when (it) {
                    is DatasResult.Success<*> -> formatInternetDataToDb(it.data as List<Employee>)//setData(it.data as? List<Employee>)
                    is DatasResult.Error -> setError(it.error)
                }
            }
        }
    }

    fun formatInternetDataToDb(data: List<Employee>) {
        //Можно показать сообщение что данные загружены и сохраняются на локальном устройстве
        data.forEach {
            val f_name = it.f_name.dbNameFormat()
            val l_name = it.l_name.dbNameFormat()
            val birthday =  it.birthday.dbDataFormat()


            Log.d("myLOG", "${it.birthday} <> $birthday")
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        internetChanel.cancel()
    }


}