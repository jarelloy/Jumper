package com.game.jumper.database.repository

import com.game.jumper.database.dao.PlayerDao
import com.game.jumper.database.entity.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepository(private val playerDao: PlayerDao) {
    suspend fun insertPlayer(player: Player) {
        withContext(Dispatchers.IO) {
            playerDao.insert(player)
        }
    }

    suspend fun getPlayerByUsername(username: String): Player? {
        return withContext(Dispatchers.IO) {
            playerDao.findByUsername(username)
        }
    }
}