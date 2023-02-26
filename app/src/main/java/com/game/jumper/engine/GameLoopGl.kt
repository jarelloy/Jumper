/*************************************************************************
\file   GameLoopGl.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of the game loop
 *************************************************************************/
package com.game.jumper.engine

import android.content.Context
import android.os.Looper
import com.game.jumper.game.JumperScene
import com.game.jumper.graphics.JumperGLRenderer

/**
 *  Game loop class
 *
 *  This class run as a thread where the entire game is running
 *
 *  @param context the context of the activity
 *  @param renderer reference to the graphics renderer
 */
class GameLoopGl(context: Context, var renderer: JumperGLRenderer) : Thread() {

    private lateinit var scene : JumperScene
    private var context = context

    private var isRunning = false
    //private var scene = Scene()
    var averageUPS // Updates per second
            = 0.0
        private set
    var averageFPS = 0.0
        private set
    var lastTime = 0.0

    /**
     * Stars the game loop thread by first loading the scene
     */
    fun startLoop()
    {
        scene = JumperScene(context)
        isRunning = true
        renderer.loadScene(scene)

        start()
    }

    /**
     * Returns the context stored in the class
     */
    fun getCurrentContext() : Context {
        return context
    }

    /**
     * Sets the pause value of the scene based on [pause]
     */
    fun setScenePause(pause : Boolean) {
        //scene = SampleScene(context)
        scene.paused = pause
    }

    /**
     * Run the gameloop thread
     *
     * While the scene is running, calculate the delta time and update the scene and render draw
     * according to the delta time
     */
    override fun run() {
        super.run()
        // time and cycle count variables
        var updateCount = 0
        var frameCount = 0
        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long
        Looper.prepare()

        scene.start()
        // Game loop
        startTime = System.currentTimeMillis()
        while (isRunning) {
            scene.update()
            renderer.update()

            updateCount++
            renderer.draw()
            frameCount++

            // Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime
            sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // Skip frames to keep up with target UPS
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                renderer.update()
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()
            }

            // Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime)
                averageFPS = updateCount / (1E-3 * elapsedTime)
                deltaTime = averageUPS / 1000
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
        }
    }

    companion object {
        private const val MAX_UPS = 60.0
        private const val UPS_PERIOD = 1E+3 / MAX_UPS
        var deltaTime : Double = 0.0
    }
}