package edu.singaporetech.iwsp

class Datasource {
    fun loadScore(): List<Score> {
        return listOf<Score>(
            Score("abc", 999),
            Score("ddd", 800),
            Score("eee", 700),
            Score("fff", 666),
            Score("123", 369),
            Score("q1w", 0),
        )
    }
}

