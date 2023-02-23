package com.game.jumper.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.game.jumper.database.entity.PowerUp

@Dao
interface PowerUpDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(powerUp: PowerUp)

    @Query("SELECT * FROM power_ups_table")
    fun getAllPowerUps(): LiveData<List<PowerUp>>

    @Query("SELECT COUNT(*) FROM power_ups_table")
    suspend fun getCount(): Int
}
