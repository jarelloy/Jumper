package com.game.jumper.graphics

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.GLUtils
import android.opengl.Matrix
import android.util.Log
import com.game.jumper.engine.GameGl
import com.game.jumper.engine.GameLoopGl
import com.game.jumper.game.SampleScene
import com.game.jumper.math.Vector2
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

open class JumperGLRenderer(context: Context) : GLSurfaceView.Renderer {
    lateinit var mQuad: JumperQuad
    lateinit var gameLoopGl: GameLoopGl
    lateinit var scene : SampleScene
    lateinit var surfaceView: GameGl

    var gameContext: Context = context

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

        var camPos: Vector2 = Vector2(0f, 0f)

        fun setCamPos(x: Float, y: Float) {
            camPos = Vector2(x, y)
        }

        fun getCamPos(): Vector2 {
            return camPos
        }
    }

    fun loadGameLoop(gameLoop: GameLoopGl) {
        gameLoopGl = gameLoop
    }

    fun loadScene(newScene: SampleScene) {
        scene = newScene
    }

    fun loadView(view: GameGl) {
        surfaceView = view
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // set bg color to black
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f)

        // load textured quads here
        // first param is gameContext
        // second param is texture path - "art/idle.png"
        mQuad = JumperQuad(gameContext, "art/BPlayer_Idle.png")

        gameLoopGl.startLoop()
    }

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

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        //mQuad.drawTextured(vPMatrix)
        scene.draw(vPMatrix)
        //Log.d("Test", "Yo")
    }

    fun draw()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        // JumperQuad has 2 draw functions - draw and drawTextured
        mQuad.drawTextured(vPMatrix)
    }

    fun update()
    {
        surfaceView.requestRender()
    }
}