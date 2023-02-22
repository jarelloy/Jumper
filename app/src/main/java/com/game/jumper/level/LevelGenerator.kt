package com.game.jumper.level

import android.graphics.Point
import com.game.jumper.level.Platform
import com.game.jumper.level.Player
import com.game.jumper.engine.GameObject

class LevelGenerator {
    fun generateLevel(screenWidth: Int, screenHeight: Int, numPlatforms : Int = 30) : Array<Platform> {
    //fun generateLevel(screenWidth: Int, screenHeight: Int, playerPos: Point) : Array<Platform> {
        val platforms = mutableListOf<Platform>()

        val doubleScreenHeight = screenHeight * 2

        // Generate random platforms
        val numberOfPredeteminedPlatform = numPlatforms/4*3
        val predetermindedgap = doubleScreenHeight / numberOfPredeteminedPlatform

        for (i in 0..numberOfPredeteminedPlatform) {
            val x = (0..screenWidth).random()
            val y = predetermindedgap * i
            val platform = when (val type = PlatformType.values().random()) {
                PlatformType.NORMAL -> Platform(x, y, type)
                PlatformType.BREAKABLE -> Platform(x, y, type)
            }
            platforms.add(platform)
        }

        // random platforms
        for (i in (numberOfPredeteminedPlatform+1) until numPlatforms) {
            val x = (0..screenWidth).random()
            val y = (0..doubleScreenHeight).random()
            val platform = when (val type = PlatformType.values().random()) {
                PlatformType.NORMAL -> Platform(x, y, type)
                PlatformType.BREAKABLE -> Platform(x, y, type)
            }
            platforms.add(platform)
        }

        /*// Add player to the world
        val player = Player(playerPosition.x, playerPosition.y)

        // Combine all the game objects into an array
        val gameObjects = mutableListOf<GameObject>()
        gameObjects.addAll(platforms)
        gameObjects.add(player)*/

        // Return the game objects as an array
        return platforms.toTypedArray()
    }

}