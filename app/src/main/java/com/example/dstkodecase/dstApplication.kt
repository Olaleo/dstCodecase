package com.example.dstkodecase

import android.app.Application
import com.example.dstkodecase.server.dstService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class dstApplication : Application() {

    val apiService: dstService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.statbank.dk/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build().create(dstService::class.java)
    }
}