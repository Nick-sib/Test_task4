package com.nickolay.testtask65app.data

import com.nickolay.testtask65app.App
import com.nickolay.testtask65app.data.provider.DataProvider
import com.nickolay.testtask65app.data.provider.InternetProvider
import com.nickolay.testtask65app.data.roomdb.DbRoom
import com.nickolay.testtask65app.data.roomdb.crosstab.CrossTabModel
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class DataProviderImpl: DataProvider {

    val database: DbRoom by lazy {
        DbRoom.getDatabase(App.instance)
    }

    private val internetProvider: InternetProvider by lazy {
        InternetProvider()
    }

    private val dbEmployees = database.getEmployeesDao()
    private val dbSpecialty = database.getSpecialtyDao()
    private val dbCrossTab = database.getCrossTabDao()

    override fun loadAllData() = internetProvider.getInternetData()

    override fun addEmployee(f_name: String, l_name: String, birthday: String, avatr_url: String) =
        dbEmployees.insert(EmployeesModel(
            f_name = f_name,
            l_name = l_name,
            birthday = birthday,
            avatr_url = avatr_url
        ))

    override fun addSpecialty(specialtyId: Long, specialtyName: String) =
        dbSpecialty.insert(SpecialtyModel(
            specialtyId, specialtyName
        ))

    override fun addCrossData(employeeId: Long, specialtyId: Long) =
        dbCrossTab.insert(
            CrossTabModel(
                specialtyId = specialtyId,
                employeesId =  employeeId
            )
        )

    override fun clearAllDatas(){
        dbEmployees.deleteAll()
        dbSpecialty.deleteAll()
        dbCrossTab.deleteAll()
    }

    override suspend fun getAllSpecialties():List<SpecialtyModel> = suspendCoroutine{
        it.resume(dbSpecialty.getSpecialty())
    }

    override suspend fun getEmployeesById(id: Long): List<EmployeesModel> = suspendCoroutine{
        it.resume(dbEmployees.getEmployees(id))
    }

    override suspend fun getUserSpecialties(EmployeeId: Long): List<String> = suspendCoroutine {
        it.resume(dbCrossTab.getUserSpecialties(EmployeeId))
    }


}