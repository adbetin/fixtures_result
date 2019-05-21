package com.andresbetin.fixturesresult.ui.viewHolder

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.andresbetin.fixturesresult.R
import com.andresbetin.fixturesresult.model.Result
import com.andresbetin.fixturesresult.util.toVenueDate
import kotlinx.android.synthetic.main.item_result.view.*

class ResultViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    fun onBind(result: Result) {
        view.result_league.text = result.competitionStage.competition.name
        view.result_venue.text =  "${result.venue.name} | ${result.date.toVenueDate()}"

        view.result_home_team_name.text = result.homeTeam.name
        view.result_home_team_score.text = result.score.home.toString()

        view.result_away_team_name.text = result.awayTeam.name
        view.result_away_team_score.text = result.score.away.toString()

        val resultColor = ContextCompat.getColor(context, R.color.colorAccent)
        val defaultColor = ContextCompat.getColor(context, R.color.text_header)
        if(result.score.winner == "home"){
            view.result_home_team_score.setTextColor(resultColor)
            view.result_away_team_score.setTextColor(defaultColor)
        }else if(result.score.winner == "away"){
            view.result_home_team_score.setTextColor(defaultColor)
            view.result_away_team_score.setTextColor(resultColor)
        }else{
            view.result_home_team_score.setTextColor(defaultColor)
            view.result_away_team_score.setTextColor(defaultColor)
        }
    }

}