package com.nickolay.testtask65app.data.provider

import com.google.gson.Gson
import com.nickolay.testtask65app.data.entity.Employees
import com.nickolay.testtask65app.data.model.DatasResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


object ThreadProvider {

    //Можно конечно сделать через Retrofit или Okhttp но пока достаточно этого и не стоит пладить зависимостей где это не требуется
    //Ошибка Cleartext HTTP traffic not permitted - пока решена добавлением `s` к протоколу

    private val URl = URL("https://gitlab.65apps.com/65gb/static/raw/master/testTask.json")
    private const val TIMEOUT = 10000
    private const val METHOD = "GET"

    fun getAllEmployees() : ReceiveChannel<DatasResult> = Channel<DatasResult>(
        Channel.CONFLATED).apply {
        //TODO: Check connection, if have not internet show "no intenet view"

        Thread {
            val connection: HttpURLConnection = (URl.openConnection() as HttpURLConnection).apply{
                requestMethod = METHOD
                connectTimeout = TIMEOUT
            }
            try {
                val inStream = BufferedReader(InputStreamReader(connection.inputStream))
                var result = inStream.readLine()
                inStream.forEachLine {
                    result = "$result $it"
                }
                val value = result?.let {
                    val jsonData = Gson().fromJson(it, Employees::class.java)
                    DatasResult.Success(jsonData.response)
                } ?: {
                    DatasResult.Error(Throwable("EMPTY RESULT"))
                }

                offer(value as DatasResult)

            }  catch (e: Exception) {
                offer(DatasResult.Error(e))
            }
            finally {
                connection.disconnect()
            }
        }.start()
    }
}
