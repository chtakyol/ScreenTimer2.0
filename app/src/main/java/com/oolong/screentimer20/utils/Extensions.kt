package com.oolong.screentimer20.utils

internal fun Int.getHours(): String {
    return if (this / 100 < 10) "0${(this / 100)}" else "${(this / 100)}"
}

internal fun Int.getMinutes(): String {
    return if (this - this / 100 * 100 < 10) "0${this - this / 100 * 100}" else "${(this - this / 100 * 100)}"
}