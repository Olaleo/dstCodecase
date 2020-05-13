package com.example.dstkodecase

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dstkodecase.adapters.SubjectAdapter
import com.example.dstkodecase.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val model: MainViewModel by viewModels()

        model.getSubjects().observe(this, Observer { subjects ->

            val adapter =
                SubjectAdapter(subjects) { subjectID, tablesCallBack ->
                    model.tables(subjectID)
                        .observe(this, Observer { tables -> tablesCallBack(tables) })
                }
            recyclerview.layoutManager = LinearLayoutManager(this)
            recyclerview.adapter = adapter
        })
    }
}
