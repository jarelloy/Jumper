package com.game.jumper.layout

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.game.jumper.databinding.ActivityGameBinding
import com.game.jumper.engine.GameGl
import com.game.jumper.graphics.JumperGLSurfaceView

class GameActivity :AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game : GameGl
    private lateinit var glSurfaceView : JumperGLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = GameGl(this)
        glSurfaceView = JumperGLSurfaceView(this)
        setContentView(game)

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

        }
}