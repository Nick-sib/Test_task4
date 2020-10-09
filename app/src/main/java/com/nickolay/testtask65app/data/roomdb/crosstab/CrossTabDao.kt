package com.nickolay.testtask65app.data.roomdb.crosstab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel

@Dao
interface CrossTabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CrossTabModel)
}