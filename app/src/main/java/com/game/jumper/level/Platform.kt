/*************************************************************************
\file   Platform.kt
\author Jeremiah Lim Eng Keng, 2002408
\date   Feb 20, 2023
\brief  This file contains the implementation of the Platform object
 *************************************************************************/

package com.game.jumper.level

/**
 * Platfrom object
 * @param x Position of the platform
 * @param y Position of the platform
 */
class Platform (val x: Float, val y: Float) {
    val sizeX : Float = 3f
    val sizeY : Float = 0.5f
    var isJumped : Boolean = false
}