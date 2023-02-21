package com.game.jumper.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "power_ups_table")
data class PowerUp(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "power_up_name") val name: String,
    @ColumnInfo(name = "power_up_desc") val description: String,
    @ColumnInfo(name = "power_up_type") val powerUpType: String,
    @ColumnInfo(name = "power_up_is_consumable") val isConsumable: Boolean,
    @ColumnInfo(name = "power_up_consumable_count") val consumableCount: Int
)