package com.game.jumper.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.game.jumper.database.JumperDatabase
import com.game.jumper.database.entity.PowerUp
import com.game.jumper.database.repository.PowerUpRepository
import kotlinx.coroutines.launch

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