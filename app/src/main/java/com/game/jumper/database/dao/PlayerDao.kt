package com.game.jumper.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.game.jumper.database.entity.Player

@Dao
interface PlayerDao {
    @Insert
    fun insert(player: Player)

    @Query("SELECT * FROM player_table WHERE username = :username")
    fun findByUsername(username: String): Player?
}
