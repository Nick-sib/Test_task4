package com.nickolay.testtask65app.data.roomdb.employees

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nickolay.testtask65app.data.entity.Specialty

@Entity(tableName = "employees_table")
data class EmployeesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val f_name: String,
    val l_name: String,
    val birthday: String,
    val avatr_url: String,
    val specialtyId:Long)
