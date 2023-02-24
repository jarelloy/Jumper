/**
 * Filename: LevelGenerator.kt
 * author: Jeremiah Lim Eng Keng
 * param: 2002408@sit.singaporetech.edu.sg
 * param: 18 Feb 2022
 * Brief: Implementation of LevelGenerator class
 * @
 */
package com.game.jumper.level

import android.graphics.Point
import com.game.jumper.level.Platform
import com.game.jumper.level.Player
import com.game.jumper.engine.GameObject

class LevelGenerator {
    /**
     *  Creates the level by using getting the screen width, screen height and number of platforms
     * 
     *  @param screenWidth Width of the level size
     *  @param screenHeight Height of the level size
     *  @return returns a mutableList of platforms
     */
    fun generateLevel(screenWidth: Int, screenHeight: Int, numPlatforms : Int = 30) : MutableList<Platform> {
    //fun generateLevel(screenWidth: Int, screenHeight: Int, playerPos: Point) : Array<Platform> {
        val platforms = mutableListOf<Platform>()

        val doubleScreenHeight = screenHeight * 2

        // Generate random platforms
        val numberOfPredeterminedPlatform = numPlatforms/4*3
        val predeterminedgap = doubleScreenHeight / numberOfPredeterminedPlatform

        for (i in 0..numberOfPredeterminedPlatform) {
            val x = (0..screenWidth).random().toFloat()
            val y = (predeterminedgap * i).toFloat()
            val platform = when (val type = PlatformType.values().random()) {
                PlatformType.NORMAL -> Platform(x, y, type)
                PlatformType.BREAKABLE -> Platform(x, y, type)
            }
            platforms.add(platform)
        }

        // random platforms
        for (i in (numberOfPredeterminedPlatform+1) until numPlatforms) {
            val x = (0..screenWidth).random().toFloat()
            val y = (0..doubleScreenHeight).random().toFloat()
            val platform = when (val type = PlatformType.values().random()) {
                PlatformType.NORMAL -> Platform(x, y, type)
                PlatformType.BREAKABLE -> Platform(x, y, type)
            }
            platforms.add(platform)
        }

        // Return the game objects as an array
        return platforms.toMutableList()
    }

}