package com.game.jumper.database.repository

/*************************************************************************
\file   PowerUpRepository.kt
\author Chua Yip Xuan, 2001488
\date   Feb 24, 2023
\brief  This file consist of a class for PowerUpRepository
 *************************************************************************/
import androidx.lifecycle.LiveData
import com.game.jumper.database.dao.PowerUpDao
import com.game.jumper.database.entity.PowerUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
/*************************************************************************
 *   /brief  A class for PowerUpRepository
 *************************************************************************/
class PowerUpRepository(private val powerUpDao: PowerUpDao) {

    val getPowerUps : LiveData<List<PowerUp>> = powerUpDao.getAllPowerUps()
    suspend fun insertPowerUp(powerUp: PowerUp) {
            powerUpDao.insert(powerUp)
    }

    fun getCount(): Int = runBlocking {
        val count = async {
            powerUpDao.getCount()
        }
        count.start()
        count.await()
    }

}