package com.nickolay.testtask65app


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

const val defBirthday: String = "-"
const val DATEFORMAT = "dd.MM.yyyy"
val outputDateFormat = SimpleDateFormat(DATEFORMAT)

fun String.dbNameFormat() = this.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)

@SuppressLint("SimpleDateFormat")
fun String?.dbDataFormat() =
    this?.let{ bDay ->
        val result = bDay.split("-")
        if (result.size < 3) defBirthday else {
            //val outputDateFormat = SimpleDateFormat("dd.MM.yyyy")
            val df = if (result[0].length > 2) SimpleDateFormat("yyyy-MM-dd") else SimpleDateFormat(
                "dd-MM-yyyy"
            )
            val parsedBirthday = df.parse(bDay)
            parsedBirthday?.let{
                outputDateFormat.format(it)// result
            } ?: defBirthday
        }
    } ?: defBirthday

fun String?.dbURLFormat() = this ?: ""



fun String.getAge() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        getAge26(this)
     else
        getAge14(this)



@RequiresApi(Build.VERSION_CODES.O)
fun getAge26(value: String) = LocalDate.parse(value, DateTimeFormatter.ofPattern(DATEFORMAT))?.let {
    "${Period.between(it, LocalDate.now()).years} лет"
} ?: defBirthday

@RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
fun getAge14(value: String) = try {
    outputDateFormat.parse(value)?.let{
        val formatter: DateFormat = SimpleDateFormat("yyyyMMdd")
        val yStart = formatter.format(it).toInt()
        val yEnd = formatter.format(Calendar.getInstance().time).toInt()
        "${(yEnd - yStart) / 10000} лет"
    } ?: defBirthday
} catch (e: Exception) {
    defBirthday
}






