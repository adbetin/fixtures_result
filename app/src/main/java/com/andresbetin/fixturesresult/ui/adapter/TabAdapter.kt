package com.andresbetin.fixturesresult.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.andresbetin.fixturesresult.R
import com.andresbetin.fixturesresult.ui.fragment.FixtureFragment
import com.andresbetin.fixturesresult.ui.fragment.ResultFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class TabAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = listOf(
        FixtureFragment(),
        ResultFragment()
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return fragments.size
    }
}