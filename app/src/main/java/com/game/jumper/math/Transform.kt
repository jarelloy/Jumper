/*************************************************************************
\file   Vector.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of the Transform class
 *************************************************************************/

package com.game.jumper.math

import android.graphics.Matrix

/**
 * Transform class
 *
 * This class represents a 3x3 transformation in homogeneous coordinates
 *
 * @param position position of the transform in 2D
 * @param rotation angle of the rotation
 * @param scale scale of the transform in 2D
 */
class Transform(var position: Vector2 = Vector2(),
                var rotation: Float = 0F,
                var scale: Vector2 = Vector2(1f, 1f)){

    /**
     * Returns the transform as a 3x3 matrix in row major format
     */
    fun getTrans() : Matrix {
        val mat3 = Matrix()
        mat3.postScale(scale.x, scale.y)
        mat3.postRotate(rotation)
        mat3.postTranslate(position.x, position.y)
        return mat3
    }
}