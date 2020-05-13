package com.example.dstkodecase.model

class Table {
    lateinit var id: String
    lateinit var text: String
    lateinit var unit: String
    lateinit var updated: String
    lateinit var firstPeriod: String
    lateinit var latestPeriod: String
    var active: Boolean = false
    lateinit var variables: List<String>
}
