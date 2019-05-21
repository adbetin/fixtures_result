package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Team {
    @SerializedName("id")
    @Expose
    val id: Long = 0

    @SerializedName("name")
    @Expose
    val name: String = ""

    @SerializedName("shortName")
    @Expose
    val shortName: String = ""

    @SerializedName("abbr")
    @Expose
    val abbr: String = ""

    @SerializedName("alias")
    @Expose
    val alias: String = ""
}