package com.nickolay.testtask65app


import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val defBirthday: String = "-"


fun String.dbNameFormat() = this.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)

@SuppressLint("SimpleDateFormat")
fun String?.dbDataFormat() =
    this?.let{ bDay ->
        val result = bDay.split("-")
        if (result.size < 3) defBirthday else {
            val outputDateFormat = SimpleDateFormat("dd.MM.yyyy")
            val df = if (result[0].length > 2) SimpleDateFormat("yyyy-MM-dd") else SimpleDateFormat("dd-MM-yyyy")
            val parsedBirthday = df.parse(bDay)
            parsedBirthday?.let{
                outputDateFormat.format(it)// result
            } ?: defBirthday
        }
    } ?: defBirthday

fun String?.dbURLFormat() = this ?: ""

