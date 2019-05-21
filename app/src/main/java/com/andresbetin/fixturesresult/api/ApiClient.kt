package com.andresbetin.fixturesresult.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object Client {
        private const val BASE_URL: String = "https://storage.googleapis.com/cdn-og-test-api/test-task/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}