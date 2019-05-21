package com.andresbetin.fixturesresult.ui.item

abstract class ListItem {

    companion object {
        val TYPE_HEADER = 0
        val TYPE_FIXTURE = 1
        val TYPE_RESULT = 2
    }

    abstract fun getType(): Int
}