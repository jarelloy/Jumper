package com.game.jumper.engine

import android.util.Log
import android.view.SurfaceHolder
import com.game.jumper.Graphics.JumperGLRenderer

class GameLoopGl(var renderer: JumperGLRenderer, private val surfaceHolder: SurfaceHolder) : Thread() {
    private var isRunning = false
    var averageUPS // Updates per second
            = 0.0
        private set
    var averageFPS = 0.0
        private set

    fun startLoop() {
        isRunning = true
        start()
    }

    override fun run() {
        super.run()
        // time and cycle count variables
        var updateCount = 0
        var frameCount = 0
        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long

        // Game loop
        startTime = System.currentTimeMillis()
        while (isRunning) {
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
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
                Log.d("Average UPS: ", averageUPS.toString())
                Log.d("Average FPS: ", averageFPS.toString())
            }
        }
    }

    companion object {
        private const val MAX_UPS = 60.0
        private const val UPS_PERIOD = 1E+3 / MAX_UPS
        var deltaTime : Float = 0F
    }
}