package com.game.jumper.Graphics

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.game.jumper.GameLoopGl
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

open class JumperGLRenderer() : GLSurfaceView.Renderer {
    lateinit var mQuad: JumperQuad
    lateinit var gameLoopGl: GameLoopGl
    companion object {
        fun loadShader(type: Int, shaderCode: String): Int {
            // create a shader based on type
            var shader: Int = GLES20.glCreateShader(type)

            // add source code to shader and compile
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)

            return shader
        }
    }

    fun loadGameLoop(gameLoop: GameLoopGl) {
        gameLoopGl = gameLoop
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // set bg color to black
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        mQuad = JumperQuad()
        gameLoopGl.startLoop()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        mQuad.draw()
    }

    fun draw()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        mQuad.draw()
    }

    fun update()
    {

    }
}