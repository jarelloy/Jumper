package com.game.jumper.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_name") val username: String,
    @ColumnInfo(name = "user_score") val score: Int
)