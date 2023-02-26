/*!*****************************************************************************
\file			GameActivity.kt
\author			Jarell Ow Yong
\par DP email:	o.yongjarell@digipen.edu
\par Course:	CSD3156
\par Project:	Jumper Game
\date			25/2/2023
\brief

This file contains the function definition used in Jumper for GameActivity

\copyright	Copyright (C) 2021 DigiPen Institute of Technology.
			Reproduction or disclosure of this file or its contents without the
			prior written consent of DigiPen Institute of Technology is prohibited.
*******************************************************************************/

package com.game.jumper.layout

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.game.jumper.R
import com.game.jumper.databinding.ActivityGameBinding
import com.game.jumper.engine.GameGl
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.model.HighScoreViewModel

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game : GameGl
    private lateinit var highScoreViewModel : HighScoreViewModel
    private var glRenderer = JumperGLRenderer(this)
    private lateinit var pauseDialog: Dialog

    lateinit var runnable: Runnable

    companion object {
        var score: Int = 0
        var scoreText: TextView? = null

        fun UpdateScore(value: Int) {
            score = value
        }
    }

    /*
    onCreate function override to be called when first starting the game activity
    This function uses the activity_game.xml file for the necessary layout.
    This function also stores the highscore whenever the player loses and shows a pause dialog
    when the game is paused
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = GameGl(this)
        setContentView(game)

        //Initializing the highscore to be shown in game
        highScoreViewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)

        val pauseMenu = TextView(this)
        //Initializing the dialog system from android
        pauseDialog = Dialog(this)
        pauseDialog.setContentView(R.layout.popup_pause)
        pauseDialog.setCancelable(false)

        //Creating a pause button
        val pauseBtn = Button(this)
        pauseBtn.text = "Pause"
        pauseBtn.textSize = 24f
        pauseBtn.x = 100f
        pauseBtn.y = 100f
        addContentView(pauseBtn, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        var pauseMenuAdded = false // Set a flag to keep track of whether the pauseMenu view has been added

        pauseBtn.setOnClickListener {
            //disable the pause button when selected
            pauseBtn.isEnabled = false
            pauseBtn.alpha = 0f

            //set game to pause, delta time = 0
            game.gameLoop.setScenePause(true)

            if (!pauseMenuAdded) {
                pauseMenuAdded = true
                pauseDialog.show()

                // touch anywhere on the popup to unpause the game
                pauseDialog.findViewById<View>(R.id.pause_popup)?.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        pauseDialog.dismiss()
                        pauseBtn.isEnabled = true
                        pauseBtn.alpha = 1f
                        pauseMenuAdded = false
                        game.gameLoop.setScenePause(false)
                        true
                    } else {
                        false
                    }
                }
            }
        }

        val typeface: Typeface = Typeface.createFromAsset(this.getAssets(), "font/minecraft.ttf")

        // Print out the score at the top right of the screen
        val scoreView = TextView(this)
        scoreView.text = "Score:"
        scoreView.textSize = 24f
        scoreView.x = 650f
        scoreView.y = 100f
        scoreView.setTextColor(Color.WHITE)
        scoreView.setTypeface(typeface)
        addContentView(scoreView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        scoreText = TextView(this)
        scoreText!!.text = score.toString()
        scoreText!!.textSize = 24f
        scoreText!!.x = 870f
        scoreText!!.y = 100f
        scoreText!!.setTextColor(Color.WHITE)
        scoreText!!.setTypeface(typeface)
        addContentView(scoreText, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        runOnUiThread {
            runnable = Runnable {

                //do something here
                scoreText!!.text = score.toString()
                runDelayedHandler(1000)
            }
        }

        runnable.run()
    }

    fun runDelayedHandler(timeToWait: Long) {

        //Keep it running
        val handler = Handler()
        handler.postDelayed(runnable, timeToWait)
    }
}