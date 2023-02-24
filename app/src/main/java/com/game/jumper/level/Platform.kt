/**
 * Filename: Platform.kt
 * Author: Jeremiah Lim Eng Keng
 * Email: 2002408@sit.singaporetech.edu.sg
 * Date: 18 Feb 2022
 */

package com.game.jumper.level

enum class PlatformType {
    NORMAL, BREAKABLE
}

class Platform (val x: Float, val y: Float, val type: PlatformType = PlatformType.NORMAL) {
    val sizeX : Float = 3f
    val sizeY : Float = 0.5f
    var isJumped : Boolean = false
}