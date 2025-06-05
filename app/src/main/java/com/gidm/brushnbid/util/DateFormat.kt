package com.gidm.brushnbid.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun parseUtcToLocal(utcString: String): LocalDateTime {
    val zonedUtc = ZonedDateTime.parse(utcString)
    return zonedUtc.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}

fun formatFecha(fechaIso: String): String {
    return try {
        val fecha = LocalDateTime.parse(fechaIso)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        fecha.format(formatter)
    } catch (e: Exception) {
        fechaIso
    }
}