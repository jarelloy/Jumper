package com.game.jumper.Graphics

import android.content.res.Resources
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import com.game.jumper.GameLoopGl
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

open class JumperGLRenderer() : GLSurfaceView.Renderer {
    lateinit var mQuad: JumperQuad
    lateinit var gameLoopGl: GameLoopGl

    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)

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
        val ratio: Float = width.toFloat() / height.toFloat()

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 7f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)

        mQuad.draw(vPMatrix)
    }

    fun draw()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        mQuad.draw(vPMatrix)
    }

    fun update()
    {

    }
}