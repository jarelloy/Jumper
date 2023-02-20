package com.game.jumper.level

enum class PlatformType {
    NORMAL, BREAKABLE
}

class Platform (val x: Int, val y: Int, val type: PlatformType) {
}