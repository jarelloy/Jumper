package com.game.jumper.level

enum class PlatformType {
    NORMAL, BREAKABLE
}

class Platform (val x: Int, val y: Int, val type: PlatformType) {
    val sizeX : Int = 10
    val sizeY : Int = 2
}