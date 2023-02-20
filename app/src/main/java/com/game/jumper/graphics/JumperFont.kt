package com.game.jumper.graphics

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.opengl.GLES20
import android.opengl.GLUtils
import javax.microedition.khronos.opengles.GL10


class JumperFont {

    var gameContext: Context? = null

    var xPos: Float = 0.0f
    var yPos: Float = 0.0f
    var textSize: Float = 0.0f
    var textToDraw: String = ""

    var bitmap: Bitmap? = null
    var canvas: Canvas? = null
    var textureHandle = IntArray(1)

    constructor(context: Context, x: Float = 0.0f, y: Float = 0.0f, size: Float = 0.0f, text: String = "") {

        gameContext = context

        xPos = x
        yPos = y
        textSize = size
        textToDraw = text

        bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap!!)
        bitmap!!.eraseColor(0)

    }

    fun setText(text: String) {
        textToDraw = text
    }

    fun setTextSize(size: Float) {
        textSize = size
    }

    fun setTextX(x: Float) {
        xPos = x
    }

    fun setTextY(y: Float) {
        yPos = y
    }

    fun drawText() {

        // get a background image from resources
        // note the image format must match the bitmap format
        var background: Drawable = gameContext!!.getResources().getDrawable(R.drawable.editbox_background)
        background.setBounds(0, 0, 256, 256)
        background.draw(canvas!!) // draw the background to our bitmap

        // Draw the text
        var textPaint = Paint()
        textPaint.setTextSize(textSize)
        textPaint.setAntiAlias(true)
        textPaint.setARGB(0xff, 0x00, 0x00, 0x00)
        canvas!!.drawText(textToDraw, xPos, yPos, textPaint)

        //Generate one texture pointer...
        GLES20.glGenTextures(1, textureHandle, 0)
        //...and bind it to our array
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle.get(0))

        //Create Nearest Filtered Texture
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR.toFloat())

        //Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT.toFloat())

        //Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)

        //Clean up
        bitmap!!.recycle()
    }
}