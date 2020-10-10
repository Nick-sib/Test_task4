package com.nickolay.testtask65app.data.roomdb.employees

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employeesModel: EmployeesModel): Long

    @Query("DELETE FROM employees_table")
    fun deleteAll()

    @Query("SELECT employees_table.* " +
                 "FROM  employees_table INNER JOIN cross_table ON employees_table.id = cross_table.employeesId " +
                 "WHERE cross_table.specialtyId = :sId")
    fun getEmployees(sId: Long): List<EmployeesModel>
}