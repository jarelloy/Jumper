package com.game.jumper.Graphics

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import com.game.jumper.GameLoopGl

open class JumperGLSurfaceView : GLSurfaceView {

    var renderer : JumperGLRenderer
    private lateinit var gameLoop : GameLoopGl

    constructor(context: Context) : super(context) {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        renderer = JumperGLRenderer()
        gameLoop = GameLoopGl(renderer, surfaceHolder)
        renderer.loadGameLoop(gameLoop)
        initOpenGLView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        renderer = JumperGLRenderer()
        gameLoop = GameLoopGl(renderer, surfaceHolder)
        renderer.loadGameLoop(gameLoop)
        initOpenGLView()
    }

    fun initOpenGLView() {
        setEGLContextClientVersion(2)
        setPreserveEGLContextOnPause(true)
        setRenderer(renderer)
        setRenderMode(RENDERMODE_WHEN_DIRTY)
    }
}