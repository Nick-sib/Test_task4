package com.nickolay.testtask65app.data.roomdb.employees

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface EmployeesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employeesModel: EmployeesModel)

    @Query("SELECT * FROM employees_table WHERE specialtyId =:idSpecialty ORDER BY f_name")
    fun getWorking(idSpecialty: Int): List<EmployeesModel>

    @Query("DELETE FROM employees_table")
    fun deleteAll()
}