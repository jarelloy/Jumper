package com.game.jumper.database.dao

/*************************************************************************
    \file   HighScoreDao.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of an interface for HighScoreDao
 *************************************************************************/

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.game.jumper.database.entity.HighScore
import kotlinx.coroutines.flow.Flow

/*************************************************************************
*   /brief  An interface for HighScoreDao
*************************************************************************/
@Dao
interface HighScoreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(highScore: HighScore)

    @Query("SELECT * FROM high_scores_table ORDER BY highscore_score DESC LIMIT 10")
    fun getHighScores(): LiveData<List<HighScore>>

    @Query("SELECT COUNT(*) FROM high_scores_table")
    suspend fun getCount(): Int
}