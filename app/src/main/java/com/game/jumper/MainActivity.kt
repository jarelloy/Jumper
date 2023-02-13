package com.game.jumper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.game.jumper.Graphics.JumperGLSurfaceView

class MainActivity : AppCompatActivity() {
    lateinit var glView : JumperGLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = JumperGLSurfaceView(this)

        setContentView(glView)
    }
}