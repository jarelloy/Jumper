package com.game.jumper.database.repository

import com.game.jumper.database.dao.PowerUpDao
import com.game.jumper.database.entity.PowerUp

class PowerUpRepository(private val powerUpDao: PowerUpDao) {
    suspend fun insert(powerUp: PowerUp) = powerUpDao.insert(powerUp)
    suspend fun getPowerUpById(id: Int) = powerUpDao.getPowerUpById(id)
    suspend fun getAllPowerUps() = powerUpDao.getAllPowerUps()
}