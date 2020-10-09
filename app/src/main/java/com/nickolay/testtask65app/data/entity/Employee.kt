package com.nickolay.testtask65app.data.entity

data class Employee(
    val f_name: String,
    val l_name: String,
    val birthday: String?,
    val avatr_url: String?,
    val specialty: Array<Specialty>)