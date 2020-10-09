package com.nickolay.testtask65app.data

import com.nickolay.testtask65app.App
import com.nickolay.testtask65app.data.entity.Employees
import com.nickolay.testtask65app.data.provider.DataProvider
import com.nickolay.testtask65app.data.provider.InternetProvider
import com.nickolay.testtask65app.data.roomdb.crosstab.CrossTabModel
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel


class DataRepository {

    private val internetProvider: DataProvider by lazy {
        InternetProvider()
    }

    private val dbEmployees = App.instance.database.getEmployeesDao()
    private val dbSpecialty = App.instance.database.getSpecialtyDao()
    private val dbCrossTab = App.instance.database.getCrossTabDao()

    fun loadAllData() = internetProvider.getInternetData()

    fun addEmployee(f_name: String, l_name: String, birthday: String, avatr_url: String) =
        dbEmployees.insert(EmployeesModel(
            f_name = f_name,
            l_name = l_name,
            birthday = birthday,
            avatr_url = avatr_url
        ))

    fun addSpecialty(specialtyId: Long, specialtyName: String) =
        dbSpecialty.insert(SpecialtyModel(
            specialtyId, specialtyName
        ))

    fun addCrossData(employeeId: Long, specialtyId: Long) =
        dbCrossTab.insert(
            CrossTabModel(
                specialtyId = specialtyId,
                employeesId =  employeeId
            )
        )

    fun clearAllDatas(){
        dbEmployees.deleteAll()
        dbSpecialty.deleteAll()
        dbCrossTab.deleteAll()
    }

    fun getAllSpecialtys() = dbSpecialty.getSpecialty()

//    suspend fun getEmployees(specialtyId: Int) =
//        dbProvider.getEmployeesDao().getEmployees(specialtyId)



    suspend fun reloadData(dataList: Employees) {

        //dbProvider.updateData(listWorkers)
    }

}