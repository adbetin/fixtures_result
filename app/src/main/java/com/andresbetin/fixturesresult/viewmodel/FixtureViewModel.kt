package com.andresbetin.fixturesresult.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.andresbetin.fixturesresult.model.Competition
import com.andresbetin.fixturesresult.model.Fixture
import com.andresbetin.fixturesresult.model.Resource
import com.andresbetin.fixturesresult.repository.FixtureRepository
import com.andresbetin.fixturesresult.util.toListOfCompetitions
import com.andresbetin.fixturesresult.util.toTitleDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FixtureViewModel : ViewModel() {

    private val fixtureRepo: FixtureRepository = FixtureRepository()
    private lateinit var fixtureData: MutableLiveData<Resource<Map<String, List<Fixture>>>>
    private val fixtureCompetitions: MutableLiveData<List<Competition>> = MutableLiveData()
    private val fixtureFilter: MutableLiveData<Competition> = MutableLiveData()

    fun getCompetitions() = fixtureCompetitions
    fun getFilter() = fixtureFilter

    fun getFixtures(): LiveData<Resource<Map<String, List<Fixture>>>> {
        if (!::fixtureData.isInitialized) {
            fixtureData = MutableLiveData()
            load()
        }
        return fixtureData
    }

    @SuppressLint("CheckResult")
    private fun load() {
        fixtureRepo.getFixtures()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onSuccess, ::onError)
    }

    private fun onSuccess(newItems: List<Fixture>) {
        var resultItems = newItems.sortedBy { it.date }
        if (fixtureFilter.value != null) {
            resultItems = resultItems.filter { it.competitionStage.competition.id == fixtureFilter.value!!.id }
        } else {
            this.fixtureCompetitions.value = newItems.toListOfCompetitions()
        }
        val result = resultItems.groupBy { it.date.toTitleDate() }
        this.fixtureData.value = Resource.success(result)
    }

    private fun onError(error: Throwable) {
        this.fixtureData.value = Resource.error(error.message!!, emptyMap())
        this.fixtureCompetitions.value = emptyList()
    }

    fun filter(competition: Competition?) {
        fixtureFilter.value = competition
        load()
    }

}