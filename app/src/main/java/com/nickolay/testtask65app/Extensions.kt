package com.nickolay.testtask65app


import java.text.SimpleDateFormat
import java.util.*

const val defBirthday: String = "-"


inline fun String.dbNameFormat() = this.toLowerCase(Locale.ROOT).capitalize(java.util.Locale.ROOT)

inline fun String?.dbDataFormat() =
    this?.let{ bDay ->
        val result = bDay.split("-")
        if (result.size < 3) defBirthday else {
            val outputDateFormat = SimpleDateFormat("dd.MM.yyyy")
            val df = if (result[0].length > 2) SimpleDateFormat("yyyy-MM-dd") else SimpleDateFormat("dd-MM-yyyy")
            val parsedBirthday = df.parse(bDay)
            outputDateFormat.format(parsedBirthday)// result
        }
    } ?: defBirthday

