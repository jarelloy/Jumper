package com.game.jumper.database.repository

import androidx.lifecycle.LiveData
import com.game.jumper.database.dao.PowerUpDao
import com.game.jumper.database.entity.PowerUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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