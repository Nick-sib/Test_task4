package com.nickolay.testtask65app.data.roomdb.employees

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "employees_table")
data class EmployeesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val f_name: String,
    val l_name: String,
    val birthday: String,
    val avatr_url: String
) : Parcelable
