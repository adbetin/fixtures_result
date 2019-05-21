package com.andresbetin.fixturesresult

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.andresbetin.fixturesresult.ui.main.PageViewModel
import com.andresbetin.fixturesresult.ui.main.PlaceholderFragment
import com.andresbetin.fixturesresult.ui.main.SectionsPagerAdapter
import com.andresbetin.fixturesresult.viewmodel.ResultViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)
        resultViewModel.getResults().observe(this, Observer {
            Snackbar.make(fab, "Resultado de carga", Snackbar.LENGTH_LONG)
                .setAction("Test", null).show()
        })

        fab.setOnClickListener { view ->
            resultViewModel.loadResults()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }
}