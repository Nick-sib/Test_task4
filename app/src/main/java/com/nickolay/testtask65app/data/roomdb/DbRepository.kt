package com.nickolay.testtask65app.data.roomdb

import com.nickolay.testtask65app.App


class DbRepository(private val dbRoom: DbRoom = App.instance.database) {

    val employees = dbRoom.getEmployeesDao().getEmployees(101)
    val specialty = dbRoom.getSpecialtyDao().getSpecialty()
}