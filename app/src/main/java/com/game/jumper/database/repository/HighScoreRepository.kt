package com.game.jumper.database.repository

import com.game.jumper.database.dao.HighScoreDao
import com.game.jumper.database.entity.HighScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HighScoreRepository(private val highScoreDao: HighScoreDao) {
    suspend fun insertHighScore(highScore: HighScore) {
        withContext(Dispatchers.IO) {
            highScoreDao.insert(highScore)
        }
    }

    suspend fun getHighScoresForUser(userId: Int, limit: Int): List<HighScore> {
        return withContext(Dispatchers.IO) {
            highScoreDao.getHighScoresForUser(userId, limit)
        }
    }
}