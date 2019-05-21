package com.andresbetin.fixturesresult.repository

import com.andresbetin.fixturesresult.api.ApiClient
import com.andresbetin.fixturesresult.api.ApiInterface
import com.andresbetin.fixturesresult.model.Fixture
import io.reactivex.Observable

class FixtureRepository {
    private val api: ApiInterface = ApiClient.create()

    fun getFixtures(): Observable<List<Fixture>> {
        return api.fixtures()
    }
}