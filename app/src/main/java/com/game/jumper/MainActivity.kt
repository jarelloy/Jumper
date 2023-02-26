/*!*****************************************************************************
\file			MainActivity.kt
\author			Jarell Ow Yong
\par DP email:	o.yongjarell@digipen.edu
\par Course:	CSD3156
\par Project:	Jumper Game
\date			25/2/2023
\brief

This file contains the function definition used in Jumper for MainActivity

\copyright	Copyright (C) 2021 DigiPen Institute of Technology.
			Reproduction or disclosure of this file or its contents without the
			prior written consent of DigiPen Institute of Technology is prohibited.
*******************************************************************************/

package com.game.jumper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.jumper.databinding.ActivityMainBinding
import com.game.jumper.layout.CustomizePlayerActivity
import com.game.jumper.layout.GameActivity
import com.game.jumper.layout.InstructionActivity
import com.game.jumper.layout.ScoreboardActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var game : GameGl
//    private lateinit var glSurfaceView : JumperGLSurfaceView

    /*
    onCreate function override to be called when first starting the activity
    This function uses the activity_main.xml file for the necessary layout.
    This function creates the main menu screen for the game which user can navigate through each
    different other activity. This is the main activity as user will be first brought into this
    screen before accessing any other sub-activities
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        //Button for play game
        binding.playgameBtn.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        //Button for instruction menu
        binding.howtoplayBtn.setOnClickListener {
            val intent = Intent (this, InstructionActivity::class.java)
            startActivity(intent)
        }

        //Button for scoreboard
        binding.scoreboardBtn.setOnClickListener {
            val intent = Intent (this, ScoreboardActivity::class.java)
            startActivity(intent)
        }

        //Button for cosmetics
        binding.customizeplayerBtn.setOnClickListener {
            val intent = Intent (this, CustomizePlayerActivity::class.java)
            startActivity(intent)
        }
        //setContentView(binding.root)

        //Button to exit the game
        binding.exitgameBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}