package com.game.jumper.database.entity

/*************************************************************************
\file   Player.kt
\author Chua Yip Xuan, 2001488
\date   Feb 24, 2023
\brief  This file consist of an data class for Player
 *************************************************************************/
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*************************************************************************
 *   /brief  A data class for Player
 *************************************************************************/
@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_name") val username: String,
    @ColumnInfo(name = "user_score") val score: Int
)