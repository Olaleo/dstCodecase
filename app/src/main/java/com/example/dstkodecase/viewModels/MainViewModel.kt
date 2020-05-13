package com.example.dstkodecase.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dstkodecase.dstApplication
import com.example.dstkodecase.model.Subject
import com.example.dstkodecase.model.Table
import com.example.dstkodecase.util.lazyMap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val subjects: MutableLiveData<List<Subject>> by lazy {
        val liveData = MutableLiveData<List<Subject>>()
        loadSubjects { list -> liveData.value = list }
        liveData
    }

    fun getSubjects(): LiveData<List<Subject>> {
        return subjects
    }

    private fun loadSubjects(onLoad: (List<Subject>) -> Unit) {
        getApplication<dstApplication>().apiService.getSubjects()
            .enqueue(object : Callback<List<Subject>> {
                override fun onFailure(call: Call<List<Subject>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<Subject>>,
                    response: Response<List<Subject>>
                ) {
                    response.body()?.let { onLoad(it) }
                }
            })
    }

    private fun getTablesOfSubject(subjectId: String, onLoad: (List<Table>) -> Unit) {
        getApplication<dstApplication>().apiService.getTablesOfSubject(subjectId)
            .enqueue(object : Callback<List<Table>> {
                override fun onFailure(call: Call<List<Table>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<List<Table>>, response: Response<List<Table>>) {
                    response.body()?.let { onLoad(it) }
                }
            })
    }

    private val contactsLiveData: Map<String, LiveData<List<Table>>> = lazyMap { subjectId ->
        val liveData = MutableLiveData<List<Table>>()
        getTablesOfSubject(subjectId) { liveData.value = it }
        return@lazyMap liveData
    }

    fun tables(subjectId: String): LiveData<List<Table>> = contactsLiveData.getValue(subjectId)
}