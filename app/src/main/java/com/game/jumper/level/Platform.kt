package com.game.jumper.level

enum class PlatformType {
    NORMAL, BREAKABLE
}

class Platform (val x: Float, val y: Float, val type: PlatformType = PlatformType.NORMAL) {
    val sizeX : Float = 3f
    val sizeY : Float = 0.2f
    var isJumped : Boolean = false
}