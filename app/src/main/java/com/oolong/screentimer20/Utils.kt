package com.oolong.screentimer20

// 360 degree -> 60 min -> 60 * 60 * 1000 millis
// 1 degree -> 6 min -> 6 * 60 * 1000 millis

fun degreeToMillis(degree: Float): Long{
    return (degree * 10 * 1000).toLong()
}

fun millisToDegree(millis: Float): Float{
    return millis / (10 * 1000)
}

fun getReadableTime(timeInMillis: Long): String {
    var t = timeInMillis
    val hours = java.util.concurrent.TimeUnit.MILLISECONDS.toHours(t)
    t -= java.util.concurrent.TimeUnit.HOURS.toMillis(hours)
    val mins = java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(t)
    t -= java.util.concurrent.TimeUnit.MINUTES.toMillis(mins)
    val secs = java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(t)
    return "$hours hours, $mins mins, $secs seconds"
}

fun getHours(timeInMillis: Long) : Int {
    return java.util.concurrent.TimeUnit.MILLISECONDS.toHours(timeInMillis).toInt()
}

fun getMinutes(timeInMillis: Long): Int {
    var t = timeInMillis
    val hours = java.util.concurrent.TimeUnit.MILLISECONDS.toHours(t)
    t -= java.util.concurrent.TimeUnit.HOURS.toMillis(hours)
    return java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(t).toInt()
}

fun getSeconds(timeInMillis: Long): Int {
    var t = timeInMillis
    val hours = java.util.concurrent.TimeUnit.MILLISECONDS.toHours(t)
    t -= java.util.concurrent.TimeUnit.HOURS.toMillis(hours)
    val mins = java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(t)
    t -= java.util.concurrent.TimeUnit.MINUTES.toMillis(mins)
    return java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(t).toInt()
}