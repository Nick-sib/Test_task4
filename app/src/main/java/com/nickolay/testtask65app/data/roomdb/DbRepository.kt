package com.nickolay.testtask65app.data.roomdb



class DbRepository(private val dbRoom: DbRoom) {

    val dddd = dbRoom.getEmployeesDao().geEmployees(101)
}