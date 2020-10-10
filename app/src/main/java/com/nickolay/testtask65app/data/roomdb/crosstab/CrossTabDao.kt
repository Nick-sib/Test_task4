package com.nickolay.testtask65app.data.roomdb.crosstab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel

@Dao
interface CrossTabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CrossTabModel)

    @Query("DELETE FROM cross_table")
    fun deleteAll()

    @Query("SELECT employeesId FROM cross_table WHERE specialtyId = :idSpecialty")
    fun getEmployees(idSpecialty: Long): List<Long>
}