/*************************************************************************
\file   JumperGLRenderer.kt
\author Leong Cheng Onn Darryl, 2000686
\date   Feb 24, 2023
\brief  This file contains the implementation of the game renderer
        using OpenGL ES
 *************************************************************************/
package com.game.jumper.graphics

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import com.game.jumper.engine.GameGl
import com.game.jumper.engine.GameLoopGl
import com.game.jumper.game.JumperScene
import com.game.jumper.math.Vector2
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Handles the drawing of gameobjects via OpenGL ES
 */
open class JumperGLRenderer(context: Context) : GLSurfaceView.Renderer {
    lateinit var mQuad: JumperQuad
    lateinit var gameLoopGl: GameLoopGl
    lateinit var scene : JumperScene
    lateinit var surfaceView: GameGl

    var gameContext: Context = context

    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)

    companion object {
        /**
         * helper function to load shaders
         */
        fun loadShader(type: Int, shaderCode: String): Int {
            // create a shader based on type
            var shader: Int = GLES20.glCreateShader(type)

            // add source code to shader and compile
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)

            return shader
        }

        var camPos: Vector2 = Vector2(0f, 0f)

        /**
         * setter function for camera
         */
        fun setCamPos(x: Float, y: Float) {
            camPos = Vector2(x, y)
        }
    }

    /**
     * setter function for current gameloop
     */
    fun loadGameLoop(gameLoop: GameLoopGl) {
        gameLoopGl = gameLoop
    }

    /**
     * setter function for scene to load
     */
    fun loadScene(newScene: JumperScene) {
        scene = newScene
    }

    /**
     * setter function for surface view
     */
    fun loadView(view: GameGl) {
        surfaceView = view
    }

    /**
     * override function to handle actions when a surface view is created
     */
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // set bg color to black
        GLES20.glClearColor(0.24f, 0.26f, .44f, 1.0f)

        // load textured quads here
        // first param is gameContext
        // second param is texture path - "art/idle.png"
        mQuad = JumperQuad(gameContext, "art/BPlayer_Idle.png")

        gameLoopGl.startLoop()
    }

    /**
     * override function to handle actions when changes are made to the surface
     */
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        val ratio: Float = width.toFloat() / height.toFloat()

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.orthoM(projectionMatrix, 0, -10f * ratio, 10f * ratio, -10f, 10f, -1f, 1f)

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f)

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)
    }

    /**
     * override function to handle rendering each frame
     */
    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        //mQuad.drawTextured(vPMatrix)
        scene.draw(vPMatrix)
        //Log.d("Test", "Yo")
    }

    /**
     * simple draw function to draw a JumperQuad
     */
    fun draw()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        // JumperQuad has 2 draw functions - draw and drawTextured
        mQuad.drawTextured(vPMatrix)
    }

    /**
     * update function handling the game's rendering each frame
     */
    fun update()
    {
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f)
        viewMatrix[12] = -camPos.x
        viewMatrix[13] = -camPos.y
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)
        surfaceView.requestRender()
    }
}