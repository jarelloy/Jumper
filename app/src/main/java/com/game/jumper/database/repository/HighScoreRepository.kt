package com.game.jumper.database.repository

import com.game.jumper.database.dao.HighScoreDao
import com.game.jumper.database.entity.HighScore

class HighScoreRepository(private val highScoreDao: HighScoreDao) {
    suspend fun insert(highScore: HighScore) = highScoreDao.insert(highScore)
    suspend fun getHighScoresForUser(userId: Int, limit: Int) = highScoreDao.getHighScoresForUser(userId, limit)
}