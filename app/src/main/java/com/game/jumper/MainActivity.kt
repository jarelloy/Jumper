package com.game.jumper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.jumper.databinding.ActivityMainBinding
import com.game.jumper.Graphics.JumperGLSurfaceView
import com.game.jumper.engine.GameGl


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var game : GameGl
    private lateinit var glSurfaceView : JumperGLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        game = GameGl(this)
        glSurfaceView = JumperGLSurfaceView(this)
        setContentView(game)
        //setContentView(binding.root)

    }
}