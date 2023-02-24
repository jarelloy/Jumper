package com.game.jumper.database.entity

/*************************************************************************
    \file   PowerUp.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of an data class for PowerUp
 *************************************************************************/
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*************************************************************************
 *   /brief  A data class for PowerUp
 *************************************************************************/
@Entity(tableName = "power_ups_table")
data class PowerUp(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "power_up_image") val image: Int,
    @ColumnInfo(name = "power_up_desc") val description: String,
    @ColumnInfo(name = "power_up_image_fp") val filepath: String
)