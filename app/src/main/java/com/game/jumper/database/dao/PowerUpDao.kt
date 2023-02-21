package com.game.jumper.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.game.jumper.database.entity.PowerUp

@Dao
interface PowerUpDao {
    @Insert
    fun insert(powerUp: PowerUp)

    @Query("SELECT * FROM power_ups_table WHERE id = :id")
    fun getPowerUpById(id: Int): PowerUp?

    @Query("SELECT * FROM power_ups_table")
    fun getAllPowerUps(): List<PowerUp>
}
