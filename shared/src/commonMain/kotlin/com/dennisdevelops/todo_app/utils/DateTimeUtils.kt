package com.dennisdevelops.todo_app.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtils {
    fun now(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun toEpochMilliseconds(localDateTime: LocalDateTime): Long = localDateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

}