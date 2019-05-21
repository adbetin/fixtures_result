package com.andresbetin.fixturesresult.api

import android.arch.lifecycle.LiveData
import com.andresbetin.fixturesresult.model.Fixture
import com.andresbetin.fixturesresult.model.Result
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("results.json")
    fun results(): Observable<List<Result>>

    @GET("fixtures.json")
    fun fixtures(): Observable<List<Fixture>>

}