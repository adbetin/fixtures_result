package com.andresbetin.fixturesresult.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.andresbetin.fixturesresult.model.Resource
import com.andresbetin.fixturesresult.model.Result
import com.andresbetin.fixturesresult.repository.ResultRepository
import com.andresbetin.fixturesresult.util.toTitleDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultViewModel : ViewModel() {

    private val resultRepo: ResultRepository = ResultRepository()
    private lateinit var resultData: MutableLiveData<Resource<Map<String, List<Result>>>>

    fun getResults(): LiveData<Resource<Map<String, List<Result>>>> {
        if (!::resultData.isInitialized) {
            resultData = MutableLiveData()
            load()
        }
        return resultData
    }

    fun loadResults() = load()

    @SuppressLint("CheckResult")
    private fun load() {
        resultRepo.getResults()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSuccess, ::onError)
    }

    private fun onSuccess(newItems: List<Result>) {
        val resultItems = newItems.sortedBy { it.date }
        val result = resultItems.groupBy { it.date.toTitleDate() }
        this.resultData.value = Resource.success(result)
    }

    private fun onError(error: Throwable) {
        this.resultData.value = Resource.error(error.message!!, emptyMap())
    }
}