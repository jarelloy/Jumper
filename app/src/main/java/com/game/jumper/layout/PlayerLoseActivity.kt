/*!*****************************************************************************
\file			PlayerLoseActivity.kt
\author			Jarell Ow Yong
\par DP email:	o.yongjarell@digipen.edu
\par Course:	CSD3156
\par Project:	Jumper Game
\date			25/2/2023
\brief

This file contains the function definition used in Jumper for PlayerLoseActivity

\copyright	Copyright (C) 2021 DigiPen Institute of Technology.
			Reproduction or disclosure of this file or its contents without the
			prior written consent of DigiPen Institute of Technology is prohibited.
*******************************************************************************/

package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.game.jumper.MainActivity
import com.game.jumper.R
import com.game.jumper.database.entity.HighScore
import com.game.jumper.databinding.ActivityLoseBinding
import com.game.jumper.model.HighScoreViewModel
import kotlin.system.exitProcess

class PlayerLoseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoseBinding
    private lateinit var highScoreViewModel : HighScoreViewModel

    /*
    onCreate function override to be called when first starting the activity
    This function uses the activity_lose.xml file for the necessary layout.
    This function also allows to see their score at the end of the game, and entering their name
    to be shown on the highscore screen.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        highScoreViewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)

        binding.editscoretext.text = GameActivity.score.toString()

        binding.savehighscoreBtn.setOnClickListener {
            insertHighScoreToDatabase(binding.editPlayerName.text.toString(), GameActivity.score)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.mmBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    //Insert score into the database to be printed on the highscore screen
    fun insertHighScoreToDatabase(userName: String, userScore: Int ) {
        val entry = HighScore(0, userName , userScore)
        highScoreViewModel.insertHighScore(entry)
        //Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
        Log.d("GameActivity", userScore.toString())
    }
}