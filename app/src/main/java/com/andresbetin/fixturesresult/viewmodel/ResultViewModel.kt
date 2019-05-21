package com.andresbetin.fixturesresult.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.andresbetin.fixturesresult.model.Resource
import com.andresbetin.fixturesresult.model.Result
import com.andresbetin.fixturesresult.repository.ResultRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultViewModel : ViewModel() {

    private val resultRepo: ResultRepository = ResultRepository()
    private lateinit var resultData: MutableLiveData<Resource<List<Result>>>

    fun getResults(): LiveData<Resource<List<Result>>> {
        if (!::resultData.isInitialized) {
            resultData = MutableLiveData()
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
        this.resultData.value = Resource.success(newItems)
    }

    private fun onError(error: Throwable) {
        this.resultData.value = Resource.error(error.message!!, mutableListOf())
    }
}