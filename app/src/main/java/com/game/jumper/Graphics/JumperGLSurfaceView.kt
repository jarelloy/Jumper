package com.game.jumper.Graphics

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

open class JumperGLSurfaceView : GLSurfaceView {

    var renderer : JumperGLRenderer

    constructor(context: Context) : super(context) {
        renderer = JumperGLRenderer()
        initOpenGLView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        renderer = JumperGLRenderer()
        initOpenGLView()
    }

    fun initOpenGLView() {
        setEGLContextClientVersion(2)
        setPreserveEGLContextOnPause(true)
        setRenderer(renderer)
        setRenderMode(RENDERMODE_WHEN_DIRTY)
    }
}