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

    suspend fun getPowerUpById(id: Int): PowerUp? {
        return withContext(Dispatchers.IO) {
            powerUpDao.getPowerUpById(id)
        }
    }

    fun getCount(): Int = runBlocking {
        val count = async {
            powerUpDao.getCount()
        }
        count.start()
        count.await()
    }

//    suspend fun getAllPowerUps(): LiveData<List<PowerUp>> {
//        return withContext(Dispatchers.IO) {
//            powerUpDao.getAllPowerUps()
//        }
//    }
}