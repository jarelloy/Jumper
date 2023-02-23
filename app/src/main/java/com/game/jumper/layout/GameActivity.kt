package com.game.jumper.layout

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.game.jumper.database.entity.HighScore
import com.game.jumper.databinding.ActivityGameBinding
import com.game.jumper.engine.GameGl
import com.game.jumper.graphics.JumperGLSurfaceView
import com.game.jumper.model.HighScoreViewModel
import org.w3c.dom.Text

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game : GameGl
    private lateinit var glSurfaceView : JumperGLSurfaceView
    private lateinit var highScoreViewModel : HighScoreViewModel

    companion object {
        var score: Int = 0
        var scoreText: TextView? = null

        fun UpdateScore(value: Int) {
            score = value
            scoreText!!.text = score.toString()
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
        val pauseBtn = Button(this)
        pauseBtn.text = "Pause"
        pauseBtn.textSize = 24f
        pauseBtn.x = 100f
        pauseBtn.y = 100f
        addContentView(pauseBtn, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        pauseBtn.setOnClickListener {
            pauseBtn.isEnabled = false
            pauseBtn.alpha = 0f

            pauseMenu.text = "Pause"
            pauseMenu.textSize = 100f
            pauseMenu.x = 150f
            pauseMenu.y = 400f
            addContentView(pauseMenu, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT))
        }

        pauseMenu.setOnClickListener {
            pauseBtn.isEnabled = true
            pauseBtn.alpha = 1f
            pauseMenu.isEnabled = false
            pauseMenu.alpha = 0f
        }

        val scoreView = TextView(this)
        scoreView.text = "Score:"
        scoreView.textSize = 24f
        scoreView.x = 650f
        scoreView.y = 100f
        addContentView(scoreView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        scoreText = TextView(this)
        scoreText!!.text = score.toString()
        scoreText!!.textSize = 24f
        scoreText!!.x = 850f
        scoreText!!.y = 100f
        addContentView(scoreText, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    fun insertHighScoreToDatabase(userName: String, userScore: Int ) {
        val entry = HighScore(0, userName , userScore)
        highScoreViewModel.insertHighScore(entry)
        //Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
        Log.d("GameActivity", userScore.toString())
    }
}