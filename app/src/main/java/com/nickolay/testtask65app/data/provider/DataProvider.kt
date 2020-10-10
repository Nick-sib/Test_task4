package com.nickolay.testtask65app.data.provider

import com.nickolay.testtask65app.data.model.DatasResult
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {

    fun loadAllData(): ReceiveChannel<DatasResult>

    fun addEmployee(f_name: String, l_name: String, birthday: String, avatr_url: String): Long
    fun addSpecialty(specialtyId: Long, specialtyName: String): Long
    fun addCrossData(employeeId: Long, specialtyId: Long)
    fun clearAllDatas()

    suspend fun getAllSpecialties():List<SpecialtyModel>
    suspend fun getEmployeesById(id: Long): List<EmployeesModel>
    suspend fun getUserSpecialties(EmployeeId: Long): List<String>
}