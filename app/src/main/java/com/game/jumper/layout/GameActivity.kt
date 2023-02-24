package com.game.jumper.layout

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.game.jumper.R
import com.game.jumper.database.entity.HighScore
import com.game.jumper.databinding.ActivityGameBinding
import com.game.jumper.engine.GameGl
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.graphics.JumperGLSurfaceView
import com.game.jumper.model.HighScoreViewModel

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game : GameGl
    private lateinit var glSurfaceView : JumperGLSurfaceView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = GameGl(this)
        glSurfaceView = JumperGLSurfaceView(this)
        setContentView(game)

        highScoreViewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)

        val pauseMenu = TextView(this)
        pauseDialog = Dialog(this)
        pauseDialog.setContentView(R.layout.popup_pause)
        pauseDialog.setCancelable(false)

        val pauseBtn = Button(this)
        pauseBtn.text = "Pause"
        pauseBtn.textSize = 24f
        pauseBtn.x = 100f
        pauseBtn.y = 100f
        addContentView(pauseBtn, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        var pauseMenuAdded = false // Set a flag to keep track of whether the pauseMenu view has been added

        pauseBtn.setOnClickListener {
            pauseBtn.isEnabled = false
            pauseBtn.alpha = 0f

            game.gameLoop.setScenePause(true)

            if (!pauseMenuAdded) {
                pauseMenuAdded = true
                pauseDialog.show()

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

        //var thread = object : Thread() {
        //    override fun run() {
        //            runOnUiThread {
        //                scoreText!!.text = score.toString()
        //            }
        //        }
        //    }
        //}
        //thread.start()
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

    fun insertHighScoreToDatabase(userName: String, userScore: Int ) {
        val entry = HighScore(0, userName , userScore)
        highScoreViewModel.insertHighScore(entry)
        //Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
        Log.d("GameActivity", userScore.toString())
    }
}