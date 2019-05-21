package com.andresbetin.fixturesresult.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.andresbetin.fixturesresult.R
import com.andresbetin.fixturesresult.ui.item.FixtureListItem
import com.andresbetin.fixturesresult.ui.item.HeaderListItem
import com.andresbetin.fixturesresult.ui.item.ListItem
import com.andresbetin.fixturesresult.ui.item.ResultListItem
import com.andresbetin.fixturesresult.ui.viewHolder.FixtureViewHolder
import com.andresbetin.fixturesresult.ui.viewHolder.HeaderViewHolder
import com.andresbetin.fixturesresult.ui.viewHolder.ResultViewHolder


class ListAdapter(private val items: List<ListItem>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ListItem.TYPE_HEADER -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(itemView)
            }
            ListItem.TYPE_FIXTURE -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fixture, parent, false)
                FixtureViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
                ResultViewHolder(itemView, context)

            }
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].getType()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.onBind((items[position] as HeaderListItem).date)
            is FixtureViewHolder -> holder.onBind((items[position] as FixtureListItem).fixture)
            is ResultViewHolder -> holder.onBind((items[position] as ResultListItem).result)
        }
    }

    override fun getItemCount(): Int = items.size

}
