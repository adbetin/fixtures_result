package com.andresbetin.fixturesresult.ui.item

import com.andresbetin.fixturesresult.model.Fixture

class FixtureListItem(val fixture: Fixture) : ListItem() {
    override fun getType(): Int = ListItem.TYPE_FIXTURE
}