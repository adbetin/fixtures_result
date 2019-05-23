package com.andresbetin.fixturesresult.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.andresbetin.fixturesresult.R
import com.andresbetin.fixturesresult.model.Competition
import com.andresbetin.fixturesresult.model.Fixture
import com.andresbetin.fixturesresult.model.Resource
import com.andresbetin.fixturesresult.ui.adapter.ListAdapter
import com.andresbetin.fixturesresult.ui.item.FixtureListItem
import com.andresbetin.fixturesresult.ui.item.HeaderListItem
import com.andresbetin.fixturesresult.ui.item.ListItem
import com.andresbetin.fixturesresult.viewmodel.FixtureViewModel
import kotlinx.android.synthetic.main.fragment_fixture_list.*

class FixtureFragment : Fragment() {

    private lateinit var fixtureViewModel: FixtureViewModel
    private var competitions: List<Competition> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fixture_list, container, false)
        fixtureViewModel = ViewModelProviders.of(this).get(FixtureViewModel::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_fixture.layoutManager = LinearLayoutManager(activity)
        fixtureViewModel.getCompetitions().observe(this, Observer { updateCompetitions(it!!) })
        fixtureViewModel.getFixtures().observe(this, Observer { updateFixtures(it!!) })
        fixtureViewModel.getFilter().observe(this, Observer { updateFilter(it) })

        val filterBtm: FloatingActionButton = activity!!.findViewById(R.id.fixture_filter_button)
        filterBtm.setOnClickListener { view ->
            showDialogOptions()
        }

        val filterCloseBtn: FloatingActionButton = activity!!.findViewById(R.id.fixture_filter_close_button)
        filterCloseBtn.setOnClickListener { view ->
            fixtureViewModel.filter(null)
        }
    }

    private fun updateFixtures(response: Resource<Map<String, List<Fixture>>>) {
        if (response.status == Resource.ERROR) {
            val toast = Toast.makeText(this.activity, response.message, Toast.LENGTH_LONG)
            toast.show()
        } else {
            val mItems = mutableListOf<ListItem>()
            for ((date, items) in response.data!!) {
                val header = HeaderListItem(date)
                mItems.add(header)
                for (fixture in items) {
                    val item = FixtureListItem(fixture)
                    mItems.add(item)
                }
            }

            list_fixture.adapter = ListAdapter(mItems, context!!)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun updateCompetitions(response: List<Competition>) {
        fixture_filter_close_button.visibility = GONE
        if (response.isNullOrEmpty()) {
            competitions = emptyList()
            fixture_filter_button.visibility = GONE
        } else {
            competitions = response
            fixture_filter_button.visibility = VISIBLE
        }
    }

    @SuppressLint("RestrictedApi")
    private fun updateFilter(competition: Competition?) {
        if (competition == null) {
            fixture_filter_close_button.visibility = GONE
            fixture_filter_button.visibility = VISIBLE
        } else {
            fixture_filter_close_button.visibility = VISIBLE
            fixture_filter_button.visibility = GONE
        }
    }

    private fun showDialogOptions() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Filter Competition")

        val itemList = competitions.map { it.name }
        builder.setItems(itemList.toTypedArray())
        { _, which ->
            fixtureViewModel.filter(competitions[which])
        }
        val dialog = builder.create()
        dialog.show()
    }
}
