/*************************************************************************
\file   GameGl.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of the game surface view
 *************************************************************************/
package com.game.jumper.engine

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.motionSensor.MotionSensorListener
import android.app.Activity

/**
 * Manages all objects in the game and is responsible for updating all states and render
 * all objects to the screen
 */
class GameGl: GLSurfaceView {
    lateinit var gameLoop: GameLoopGl
    var renderer : JumperGLRenderer

    constructor(context: Context) : super(context) {
        renderer = JumperGLRenderer(context)
        isFocusable = true
        initOpenGLView()
    }

    private fun initOpenGLView() {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        setEGLContextClientVersion(2)
        setPreserveEGLContextOnPause(true)
        setRenderer(renderer)
        setRenderMode(RENDERMODE_WHEN_DIRTY)
        renderer.loadView(this)
        gameLoop = GameLoopGl(context, renderer)
        renderer.loadGameLoop(gameLoop)
    }

    fun startGame()
    {

    }
}