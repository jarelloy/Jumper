package com.game.jumper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat

/**
 * Manages all objects in the game and is responsible for updating all states and render
 * all objects to the screen
 */
class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val gameLoop: GameLoop
    private val context: Context

    init {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        this.context = context
        gameLoop = GameLoop(this, surfaceHolder)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    override fun surfaceDestroyed(holder: SurfaceHolder) {}
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        drawFPS(canvas)
    }

    fun drawUPS(canvas: Canvas) {
        val averageUPS = java.lang.Double.toString(gameLoop.averageUPS)
        val paint = Paint()
        val color = ContextCompat.getColor(context, R.color.magenta)
        paint.color = color
        paint.textSize = 50F
        canvas.drawText("UPS: $averageUPS", 100f, 50f, paint)
    }

    fun drawFPS(canvas: Canvas) {
        val averageFPS = java.lang.Double.toString(gameLoop.averageFPS)
        val paint = Paint()
        val color = ContextCompat.getColor(context, R.color.magenta)
        paint.color = color
        paint.textSize = 50F
        canvas.drawText("FPS: $averageFPS", 100f, 100f, paint)
    }

    fun update() {

    }
}