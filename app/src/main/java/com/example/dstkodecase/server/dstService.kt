package com.example.dstkodecase.server

import com.example.dstkodecase.model.Subject
import com.example.dstkodecase.model.Table
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface dstService {

    @GET("subjects")
    fun getSubjects(@Query("recursive") recursive: Boolean = true): Call<List<Subject>>

    @GET("tables")
    fun getTablesOfSubject(@Query("subjects") subject: String): Call<List<Table>>
}