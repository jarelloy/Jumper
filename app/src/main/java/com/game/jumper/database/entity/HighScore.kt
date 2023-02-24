package com.game.jumper.database.entity

/*************************************************************************
    \file   HighScore.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of an data class for HighScore
 *************************************************************************/
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/*************************************************************************
 *   /brief  A data class for HighScore
 *************************************************************************/
@Entity(tableName = "high_scores_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "highscore_userid") val userId: String,
    @ColumnInfo(name = "highscore_score") val score: Int,
    //@ColumnInfo(name = "date_played") var datePlayed: Date? = null
)