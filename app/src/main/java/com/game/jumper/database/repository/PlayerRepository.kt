package com.game.jumper.database.repository

/*************************************************************************
    \file   PlayerRepository.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of a class for PlayerRepository which
            manages the Dao interface functions
 *************************************************************************/
import com.game.jumper.database.dao.PlayerDao
import com.game.jumper.database.entity.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
/*************************************************************************
 *   /brief  A class for PlayerRepository
 *************************************************************************/
class PlayerRepository(private val playerDao: PlayerDao) {

    /*************************************************************************
     *   /brief  This function inserts a player entity in the database
     *************************************************************************/
    suspend fun insertPlayer(player: Player) {
        withContext(Dispatchers.IO) {
            playerDao.insert(player)
        }
    }

    /*************************************************************************
     *   /brief  This function finds the Player data by username
     *************************************************************************/
    suspend fun getPlayerByUsername(username: String): Player? {
        return withContext(Dispatchers.IO) {
            playerDao.findByUsername(username)
        }
    }
}