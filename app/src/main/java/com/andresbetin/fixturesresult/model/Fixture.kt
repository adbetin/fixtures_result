package com.andresbetin.fixturesresult.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

open class Fixture {

    @SerializedName("id")
    @Expose
    val id: Long = 0

    @SerializedName("type")
    @Expose
    val type: String = ""

    @SerializedName("homeTeam")
    @Expose
    val homeTeam: Team= Team()

    @SerializedName("awayTeam")
    @Expose
    val awayTeam: Team = Team()

    @SerializedName("date")
    @Expose
    val date: Date = Date()

    @SerializedName("competitionStage")
    @Expose
    val competitionStage: CompetitionStage = CompetitionStage()

    @SerializedName("venue")
    @Expose
    val venue: Venue = Venue()

    @SerializedName("state")
    @Expose
    val state: String = ""
}