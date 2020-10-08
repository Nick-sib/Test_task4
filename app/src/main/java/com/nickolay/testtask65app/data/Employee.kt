package com.nickolay.testtask65app.data

import java.util.*

data class Employee(
    val f_name: String,
    val l_name: String,
    val birthday: Date,
    val avatr_url: String,
    val specialty: Array<Specialty>)