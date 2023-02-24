package com.game.jumper.model
/*************************************************************************
\file   PowerUpViewModel.kt
\author Chua Yip Xuan, 2001488
\date   Feb 24, 2023
\brief  This file consist of a class for PowerUpViewModel
 *************************************************************************/

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.game.jumper.database.JumperDatabase
import com.game.jumper.database.entity.PowerUp
import com.game.jumper.database.repository.PowerUpRepository
import kotlinx.coroutines.launch

/*************************************************************************
 *   /brief  A class for PowerUpViewModel
 *************************************************************************/
class PowerUpViewModel (application: Application) : AndroidViewModel(application){

    private val powerUpRepo : PowerUpRepository
    val powerUps : LiveData<List<PowerUp>>

    init {
        val powerUpDao = JumperDatabase.getDatabaseInstance(application).powerUpDao()
        powerUpRepo = PowerUpRepository(powerUpDao)
        powerUps = powerUpRepo.getPowerUps
    }

    fun insertPowerUp(powerUp: PowerUp)
    {
        viewModelScope.launch {
            powerUpRepo.insertPowerUp(powerUp)
        }
    }

    fun getCount(): Int {
        return powerUpRepo.getCount()
    }

}