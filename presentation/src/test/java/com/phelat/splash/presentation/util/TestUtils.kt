package com.phelat.splash.presentation.util

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

object TestUtils {

    fun readResource(fileName: String, classLoader: ClassLoader): String? {
        val file = File(classLoader.getResource(fileName).file)
        try {
            BufferedReader(FileReader(file)).use { bufferedReader ->
                val response = StringBuilder()
                var line: String? = bufferedReader.readLine()
                while (line != null) {
                    response.append(line)
                    response.append("\n")
                    line = bufferedReader.readLine()
                }
                return response.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

}