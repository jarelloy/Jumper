package com.game.jumper.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.game.jumper.database.entity.HighScore

@Dao
interface HighScoreDao {
    @Insert
    fun insert(highScore: HighScore)

    @Query("SELECT * FROM high_scores_table WHERE highscore_userid = :userId ORDER BY score DESC LIMIT :limit")
    fun getHighScoresForUser(userId: Int, limit: Int): List<HighScore>
}