package com.andresbetin.fixturesresult.repository

import com.andresbetin.fixturesresult.api.ApiClient
import com.andresbetin.fixturesresult.api.ApiInterface
import com.andresbetin.fixturesresult.model.Result
import io.reactivex.Observable

class ResultRepository {
    private val api: ApiInterface = ApiClient.create()

    fun getResults(): Observable<List<Result>> {
        return api.results()
    }
}