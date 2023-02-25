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
    /*************************************************************************
     *   /brief  This function inserts a PowerUp entity into the database
     *************************************************************************/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(powerUp: PowerUp)

    /*************************************************************************
     *   /brief  This function gets the list of PowerUps from the database
     *************************************************************************/
    @Query("SELECT * FROM power_ups_table")
    fun getAllPowerUps(): LiveData<List<PowerUp>>

    /*************************************************************************
     *   /brief  This function gets the count of PowerUps in the database
     *************************************************************************/
    @Query("SELECT COUNT(*) FROM power_ups_table")
    suspend fun getCount(): Int
}
