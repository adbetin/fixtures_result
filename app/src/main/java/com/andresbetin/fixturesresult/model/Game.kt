package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Game {

    @SerializedName("id")
    @Expose
    val id: Long = 0

    @SerializedName("type")
    @Expose
    var type: String = ""

}