package com.bangtiray.movietv.extension

import java.text.ParseException
import java.text.SimpleDateFormat


private fun formatDate(date: String, format: String): String {
    var result = ""

    val old = SimpleDateFormat("yyyy-MM-dd")
    try {
        val oldDate = old.parse(date)
        val newFormat = SimpleDateFormat(format)
        result = newFormat.format(oldDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return result
}

fun getStringDate(date: String): String {
    return formatDate(date, "dd MMMM yyyy")
}