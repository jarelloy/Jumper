package com.game.jumper.database.repository

import com.game.jumper.database.dao.PlayerDao
import com.game.jumper.database.entity.Player

class PlayerRepository(private val playerDao: PlayerDao) {
    suspend fun insert(player: Player) = playerDao.insert(player)
    suspend fun findByUsername(username: String) = playerDao.findByUsername(username)
}