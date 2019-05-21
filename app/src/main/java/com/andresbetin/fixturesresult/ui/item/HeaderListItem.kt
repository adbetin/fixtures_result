package com.andresbetin.fixturesresult.ui.item

class HeaderListItem(val date: String) : ListItem() {
    override fun getType(): Int = ListItem.TYPE_HEADER
}