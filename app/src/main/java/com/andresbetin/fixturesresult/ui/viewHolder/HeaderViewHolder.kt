package com.andresbetin.fixturesresult.ui.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_header.view.*

class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun onBind(date: String) {
        view.item_text.text = date
    }
}