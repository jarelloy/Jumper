package com.game.jumper.math

import kotlin.math.sqrt

data class Vector2(var x: Float = 0f, var y: Float = 0f) {
    operator fun plus(b: Vector2) : Vector2 {
        return Vector2(x + b.x, y + b.y)
    }

    operator fun minus(b: Vector2) : Vector2 {
        return Vector2(x + b.x, y + b.y)
    }

    operator fun times(b: Float) : Vector2 {
        return Vector2(x * b, y * b)
    }

    operator fun div(b: Float) : Vector2 {
        return Vector2(x / b, y / b)
    }

    fun Dot(b: Vector2): Float {
        return x * b.x + y * b.y
    }

    fun Length(): Float {
        return sqrt(x * x + y * y)
    }

    fun Normalize() {
        val len = Length()
        if(len != 0f) {
            x /= len
            y /= len
        }
    }
}

