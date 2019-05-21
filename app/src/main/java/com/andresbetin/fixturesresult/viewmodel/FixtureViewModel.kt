package com.andresbetin.fixturesresult.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.andresbetin.fixturesresult.model.Fixture
import com.andresbetin.fixturesresult.model.Resource
import com.andresbetin.fixturesresult.repository.FixtureRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FixtureViewModel : ViewModel() {

    private val fixtureRepo: FixtureRepository = FixtureRepository()
    private lateinit var fixtureData: MutableLiveData<Resource<List<Fixture>>>

    fun getFixtures(): LiveData<Resource<List<Fixture>>> {
        if (!::fixtureData.isInitialized) {
            fixtureData = MutableLiveData()
        }
        return fixtureData
    }

    fun loadResult() = load()

    @SuppressLint("CheckResult")
    private fun load() {
        fixtureRepo.getFixtures()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSuccess, ::onError)
    }

    private fun onSuccess(newItems: List<Fixture>) {
        this.fixtureData.value = Resource.success(newItems)
    }

    private fun onError(error: Throwable) {
        this.fixtureData.value = Resource.error(error.message!!, mutableListOf())
    }

}