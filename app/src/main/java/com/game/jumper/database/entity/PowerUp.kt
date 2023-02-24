package com.game.jumper.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "power_ups_table")
data class PowerUp(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "power_up_image") val image: Int,
    @ColumnInfo(name = "power_up_desc") val description: String,
)