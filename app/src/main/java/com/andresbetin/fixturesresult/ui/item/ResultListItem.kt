package com.andresbetin.fixturesresult.ui.item

import com.andresbetin.fixturesresult.model.Result

class ResultListItem(val result: Result) : ListItem() {
    override fun getType(): Int = ListItem.TYPE_RESULT
}