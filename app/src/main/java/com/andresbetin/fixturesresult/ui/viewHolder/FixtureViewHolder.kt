package com.andresbetin.fixturesresult.ui.viewHolder

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.andresbetin.fixturesresult.R
import com.andresbetin.fixturesresult.model.Fixture
import com.andresbetin.fixturesresult.util.toDayOfMonth
import com.andresbetin.fixturesresult.util.toDayOfWeek
import com.andresbetin.fixturesresult.util.toVenueDate
import kotlinx.android.synthetic.main.item_fixture.view.*

class FixtureViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    fun onBind(fixture: Fixture) {
        view.fixture_league.text = fixture.competitionStage.competition.name
        view.fixture_venue_stadium.text = "${fixture.venue.name} |"
        view.fixture_venue_date.text = fixture.date.toVenueDate()

        view.fixture_home_team_name.text = fixture.homeTeam.name
        view.fixture_away_team_name.text = fixture.awayTeam.name

        view.fixture_date.text = fixture.date.toDayOfMonth()
        view.fixture_day_of_week.text = fixture.date.toDayOfWeek()

        val resultColor = ContextCompat.getColor(context, R.color.red_background)
        val defaultColor = ContextCompat.getColor(context, android.R.color.tab_indicator_text)

        if (fixture.state != "postponed") {
            view.fixture_league_postponed.visibility = GONE
            view.fixture_venue_date.setTextColor(defaultColor)
        } else {
            view.fixture_league_postponed.visibility = VISIBLE
            view.fixture_venue_date.setTextColor(resultColor)
        }
    }
}