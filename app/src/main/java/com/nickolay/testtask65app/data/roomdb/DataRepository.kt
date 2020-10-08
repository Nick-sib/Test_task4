package com.nickolay.testtask65app.data.roomdb

import com.nickolay.testtask65app.App
import com.nickolay.testtask65app.data.DbRoom
import com.nickolay.testtask65app.data.entity.Employees
import com.nickolay.testtask65app.data.provider.DataProvider
import com.nickolay.testtask65app.data.provider.InternetProvider


class DataRepository(private val dbRoom: DbRoom) {

    private val internetProvider: DataProvider = InternetProvider()
    private val dbProvider: DbRoom = App.instance.database

    fun loadAllData() = internetProvider.getInternetData()

    suspend fun getEmployees(specialtyId: Int) =
        dbProvider.getEmployeesDao().getEmployees(specialtyId)
    suspend fun getAllSpecialty() =
        dbProvider.getSpecialtyDao()

    suspend fun reloadData(dataList: Employees) {

        //dbProvider.updateData(listWorkers)
    }

}