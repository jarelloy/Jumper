package com.game.jumper.math

import android.graphics.Matrix

class Transform(var position: Vector2 = Vector2(),
                var rotation: Float = 0F,
                var scale: Vector2 = Vector2(1f, 1f)){
    fun getTrans() : Matrix {
        val mat4 = Matrix()
        mat4.postScale(scale.x, scale.y)
        mat4.postRotate(rotation)
        mat4.postTranslate(position.x, position.y)
        return mat4
    }
}