package com.game.jumper.database.dao

/*************************************************************************
\file   PowerUpDao.kt
\author Chua Yip Xuan, 2001488
\date   Feb 24, 2023
\brief  This file consist of an interface for PowerUpDao
 *************************************************************************/
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.game.jumper.database.entity.PowerUp
/*************************************************************************
 *   /brief  An interface for PowerUpDao
 *************************************************************************/
@Dao
interface PowerUpDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(powerUp: PowerUp)

    @Query("SELECT * FROM power_ups_table")
    fun getAllPowerUps(): LiveData<List<PowerUp>>

    @Query("SELECT COUNT(*) FROM power_ups_table")
    suspend fun getCount(): Int
}
