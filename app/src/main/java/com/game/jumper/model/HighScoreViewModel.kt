package com.game.jumper.model

import android.app.Application
import androidx.lifecycle.*
import com.game.jumper.database.JumperDatabase
import com.game.jumper.database.entity.HighScore
import com.game.jumper.database.repository.HighScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

//  var highScores: LiveData<List<HighScore>> = MutableLiveData(emptyList())
//
//    fun insertHighScore(highScore: HighScore) {
//        viewModelScope.launch {
//            repository.insertHighScore(highScore)
//        }
//    }
//
//    fun loadHighScores(){
//        viewModelScope.launch {
//            val scores = repository.getHighScoresForUser()
//            (highScores as MutableLiveData<List<HighScore>>).value = scores
//        }
//    }
}
