package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CompetitionStage {

    @SerializedName("competition")
    @Expose
    val competition: Competition = Competition()

    @SerializedName("stage")
    @Expose
    val stage: String = ""

    @SerializedName("leg")
    @Expose
    val leg: String = ""
}