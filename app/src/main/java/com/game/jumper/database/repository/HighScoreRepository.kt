package com.game.jumper.database.repository

/*************************************************************************
\file   HighScoreRepository.kt
\author Chua Yip Xuan, 2001488
\date   Feb 24, 2023
\brief  This file consist of a class for HighScoreRespository
 *************************************************************************/
import androidx.lifecycle.LiveData
import com.game.jumper.database.dao.HighScoreDao
import com.game.jumper.database.entity.HighScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
/*************************************************************************
 *   /brief  A class for HighScoreRepository
 *************************************************************************/
class HighScoreRepository(private val highScoreDao: HighScoreDao) {

    val getHighScores : LiveData<List<HighScore>> = highScoreDao.getHighScores()
    suspend fun insertHighScore(highScore: HighScore) {
        highScoreDao.insert(highScore)
    }

    fun getCount(): Int = runBlocking {
        val count = async {
            highScoreDao.getCount()
        }
        count.start()
        count.await()
    }

//    suspend fun getHighScores(): LiveData<List<HighScore>> {
//        return withContext(Dispatchers.IO) {
//            highScoreDao.getHighScoresForUser()
//        }
//    }
}