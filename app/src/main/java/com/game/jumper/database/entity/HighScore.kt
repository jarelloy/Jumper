package com.game.jumper.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "high_scores_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_id") val userId: Int,
    val score: Int,
    @ColumnInfo(name = "date_played") var datePlayed: Date? = null


)