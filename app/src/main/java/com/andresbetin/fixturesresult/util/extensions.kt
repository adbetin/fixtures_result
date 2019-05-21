package com.andresbetin.fixturesresult.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toTitleDate() : String {
    val format = SimpleDateFormat("MMMM yyyy")
    return format.format(this).capitalize()
}

fun Date.toVenueDate() : String {
    val format = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm")
    return format.format(this).capitalize()
}

fun Date.toDayOfWeek() : String {
    val format = SimpleDateFormat("EE")
    return format.format(this).capitalize()
}

fun Date.toDayOfMonth() : String {
    val format = SimpleDateFormat("dd")
    return format.format(this).capitalize()
}