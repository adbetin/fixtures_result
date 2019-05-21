package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Venue {

    @SerializedName("id")
    @Expose
    val id: Long = 0

    @SerializedName("name")
    @Expose
    val name: String = ""
}