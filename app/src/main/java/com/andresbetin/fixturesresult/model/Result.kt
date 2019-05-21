package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result: Fixture() {

    @SerializedName("score")
    @Expose
    val score: Score = Score()

    @SerializedName("penaltyScore")
    @Expose
    val penaltyScore: Score? = Score()

    @SerializedName("aggregateScore")
    @Expose
    val aggregateScore: Score? = Score()

}