package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Score {

    @SerializedName("home")
    @Expose
    val home: Int = 0

    @SerializedName("away")
    @Expose
    val away: Int = 0

    @SerializedName("winner")
    @Expose
    val winner: String = ""
}