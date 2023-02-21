package com.game.jumper.database.repository

import com.game.jumper.database.dao.PowerUpDao
import com.game.jumper.database.entity.PowerUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PowerUpRepository(private val powerUpDao: PowerUpDao) {
    suspend fun insertPowerUp(powerUp: PowerUp) {
        withContext(Dispatchers.IO) {
            powerUpDao.insert(powerUp)
        }
    }

    suspend fun getPowerUpById(id: Int): PowerUp? {
        return withContext(Dispatchers.IO) {
            powerUpDao.getPowerUpById(id)
        }
    }

    suspend fun getAllPowerUps(): List<PowerUp> {
        return withContext(Dispatchers.IO) {
            powerUpDao.getAllPowerUps()
        }
    }
}