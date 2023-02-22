package com.game.jumper.graphics

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.widget.Button
import com.game.jumper.engine.GameLoopGl

open class JumperGLSurfaceView : GLSurfaceView {

    var renderer : JumperGLRenderer
    private lateinit var gameLoop : GameLoopGl

    constructor(context: Context) : super(context) {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        renderer = JumperGLRenderer(context)
        gameLoop = GameLoopGl(context, renderer)
        renderer.loadGameLoop(gameLoop)
        initOpenGLView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        renderer = JumperGLRenderer(context)
        gameLoop = GameLoopGl(context, renderer)
        renderer.loadGameLoop(gameLoop)
        initOpenGLView()
    }

    fun initOpenGLView() {
        setEGLContextClientVersion(2)
        setPreserveEGLContextOnPause(true)
        setRenderer(renderer)
    }
}