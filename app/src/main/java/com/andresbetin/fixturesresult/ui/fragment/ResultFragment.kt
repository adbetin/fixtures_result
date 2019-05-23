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
import android.view.ViewGroup
import android.widget.Toast
import com.andresbetin.fixturesresult.R
import com.andresbetin.fixturesresult.model.Competition
import com.andresbetin.fixturesresult.model.Resource
import com.andresbetin.fixturesresult.model.Result
import com.andresbetin.fixturesresult.ui.adapter.ListAdapter
import com.andresbetin.fixturesresult.ui.item.HeaderListItem
import com.andresbetin.fixturesresult.ui.item.ListItem
import com.andresbetin.fixturesresult.ui.item.ResultListItem
import com.andresbetin.fixturesresult.viewmodel.ResultViewModel
import kotlinx.android.synthetic.main.fragment_result_list.*


class ResultFragment : Fragment() {
    private lateinit var resultViewModel: ResultViewModel
    private var competitions: List<Competition> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result_list, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_result.layoutManager = LinearLayoutManager(activity)
        resultViewModel.getCompetitions().observe(this, Observer { updateCompetitions(it!!) })
        resultViewModel.getResults().observe(this, Observer { updateResults(it!!) })
        resultViewModel.getFilter().observe(this, Observer { updateFilter(it) })

        val filterBtm: FloatingActionButton = activity!!.findViewById(R.id.result_filter_button)
        filterBtm.setOnClickListener { view ->
            showDialogOptions()
        }

        val filterCloseBtn: FloatingActionButton = activity!!.findViewById(R.id.result_filter_close_button)
        filterCloseBtn.setOnClickListener { view ->
            resultViewModel.filter(null)
        }
    }

    private fun updateResults(response: Resource<Map<String, List<Result>>>) {
        if (response.status == Resource.ERROR) {
            val toast = Toast.makeText(this.activity, response.message, Toast.LENGTH_LONG)
            toast.show()
        } else {
            val mItems = mutableListOf<ListItem>()
            for ((date, items) in response.data!!) {
                val header = HeaderListItem(date)
                mItems.add(header)
                for (result in items) {
                    val item = ResultListItem(result)
                    mItems.add(item)
                }
            }

            list_result.adapter = ListAdapter(mItems, context!!)
        }
    }


    @SuppressLint("RestrictedApi")
    private fun updateCompetitions(response: List<Competition>) {
        result_filter_close_button.visibility = View.GONE
        if (response.isNullOrEmpty()) {
            competitions = emptyList()
            result_filter_button.visibility = View.GONE
        } else {
            competitions = response
            result_filter_button.visibility = View.VISIBLE
        }
    }

    @SuppressLint("RestrictedApi")
    private fun updateFilter(competition: Competition?) {
        if (competition == null) {
            result_filter_close_button.visibility = View.GONE
            result_filter_button.visibility = View.VISIBLE
        } else {
            result_filter_close_button.visibility = View.VISIBLE
            result_filter_button.visibility = View.GONE
        }
    }

    private fun showDialogOptions() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Filter Competition")

        val itemList = competitions.map { it.name }
        builder.setItems(itemList.toTypedArray())
        { _, which ->
            resultViewModel.filter(competitions[which])
        }
        val dialog = builder.create()
        dialog.show()
    }

}
