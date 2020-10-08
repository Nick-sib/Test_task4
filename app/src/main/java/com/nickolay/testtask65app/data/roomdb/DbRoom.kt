package com.nickolay.testtask65app.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesDao
import com.nickolay.testtask65app.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyDao
import com.nickolay.testtask65app.data.roomdb.specialty.SpecialtyModel


@Database(entities = [EmployeesModel::class,SpecialtyModel::class], version = 1)
abstract class DbRoom : RoomDatabase()  {
    abstract fun getEmployeesDao(): EmployeesDao
    abstract fun getSpecialtyDao(): SpecialtyDao

    companion object {
        var INSTANCE: DbRoom? = null
        fun getDatabase(context: Context) =
            INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbRoom::class.java,
                    "word_database"
                )
                    .build()
                INSTANCE = instance
                instance
        }
    }

}