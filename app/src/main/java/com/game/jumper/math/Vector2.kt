/*************************************************************************
\file   Vector2.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of a 2D vector class
 *************************************************************************/

package com.game.jumper.math

import kotlin.math.sqrt

/**
 *  Vector2 data class
 *
 *  Represents a vector in 2D space
 *
 *  @param x x coordinates
 *  @param y y coordinates
 */
data class Vector2(var x: Float = 0f, var y: Float = 0f) {

    /**
     * Operator overload of vector addition
     */
    operator fun plus(b: Vector2) : Vector2 {
        return Vector2(x + b.x, y + b.y)
    }

    /**
     * Operator overload of vector subtraction
     */
    operator fun minus(b: Vector2) : Vector2 {
        return Vector2(x + b.x, y + b.y)
    }

    /**
     * Operator oveload of scaling a vector
     */
    operator fun times(b: Float) : Vector2 {
        return Vector2(x * b, y * b)
    }

    /**
     * Operator oveload of scaling a vector as 1/[b]
     */
    operator fun div(b: Float) : Vector2 {
        return Vector2(x / b, y / b)
    }

    /**
     * Returns the dot product between this vector and [b]. Represents the length of this vector
     * projected into [b]
     */
    fun Dot(b: Vector2): Float {
        return x * b.x + y * b.y
    }

    /**
     * Returns the length of this vector
     */
    fun Length(): Float {
        return sqrt(x * x + y * y)
    }

    /**
     * Normalize the vector
     */
    fun Normalize() {
        val len = Length()
        if(len != 0f) {
            x /= len
            y /= len
        }
    }
}

