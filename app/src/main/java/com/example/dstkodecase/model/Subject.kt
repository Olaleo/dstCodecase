package com.example.dstkodecase.model

class Subject {

    lateinit var id: String
    lateinit var description: String
    val active: Boolean = false
    val hasSubjects: Boolean = false
    lateinit var subjects: List<Subject>

    lateinit var tables: List<Table>
    var isExpanded = false
}
