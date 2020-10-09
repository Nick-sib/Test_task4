package com.nickolay.testtask65app.data

import android.util.Log
import com.nickolay.testtask65app.App
import com.nickolay.testtask65app.data.roomdb.DbRoom
import com.nickolay.testtask65app.data.entity.Employees
import com.nickolay.testtask65app.data.provider.DataProvider
import com.nickolay.testtask65app.data.provider.InternetProvider
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel


class DataRepository {

    private val internetProvider: DataProvider by lazy {
        InternetProvider()
    }

    private val dbProvider: DbRoom = App.instance.database

    fun loadAllData() = internetProvider.getInternetData()

    suspend fun getEmployees(specialtyId: Int) =
        dbProvider.getEmployeesDao().getEmployees(specialtyId)
    fun getAllSpecialty(){

        Log.d("myLOG", "getAllSpecialty: ")
        //return dbProvider.getSpecialtyDao().getSpecialty()
    }



    suspend fun reloadData(dataList: Employees) {

        //dbProvider.updateData(listWorkers)
    }

}