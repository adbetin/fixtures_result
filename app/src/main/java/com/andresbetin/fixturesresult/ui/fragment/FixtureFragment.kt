package com.andresbetin.fixturesresult.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andresbetin.fixturesresult.R
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
        fixtureViewModel.getFixtures().observe(this, Observer { updateFixtures(it!!) })
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
}
