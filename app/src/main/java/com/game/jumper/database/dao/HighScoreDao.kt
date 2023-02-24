package com.game.jumper.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.game.jumper.database.entity.HighScore
import kotlinx.coroutines.flow.Flow

@Dao
interface HighScoreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(highScore: HighScore)

    @Query("SELECT * FROM high_scores_table ORDER BY highscore_score DESC LIMIT 10")
    fun getHighScores(): LiveData<List<HighScore>>

    @Query("SELECT COUNT(*) FROM high_scores_table")
    suspend fun getCount(): Int
}