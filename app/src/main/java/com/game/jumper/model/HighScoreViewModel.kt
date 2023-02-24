package com.game.jumper.model
/*************************************************************************
    \file   HighScoreViewModel.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of a class for HighScoreViewModel
 *************************************************************************/

import android.app.Application
import androidx.lifecycle.*
import com.game.jumper.database.JumperDatabase
import com.game.jumper.database.entity.HighScore
import com.game.jumper.database.repository.HighScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*************************************************************************
 *   /brief  A class for HighScoreViewModel
 *************************************************************************/
class HighScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val highScoreRepo : HighScoreRepository
    val highScores : LiveData<List<HighScore>>

    init {
        val highScoreDao = JumperDatabase.getDatabaseInstance(application).highScoreDao()
        highScoreRepo = HighScoreRepository(highScoreDao)
        highScores = highScoreRepo.getHighScores
    }

    fun insertHighScore(highScore: HighScore)
    {
        viewModelScope.launch (Dispatchers.IO){
            highScoreRepo.insertHighScore(highScore)
        }
    }

    fun getCount(): Int {
        return highScoreRepo.getCount()
    }

}
