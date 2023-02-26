/*************************************************************************
\file   LevelGenerator.kt
\author Jeremiah Lim Eng Keng, 2002408
\date   Feb 15, 2023
\brief  This file contains the implementation of LevelGenerator
 *************************************************************************/
package com.game.jumper.level

class LevelGenerator {
    /**
     *  Creates the level by using getting the screen width, screen height and number of platforms
     *
     *  /param context the context of the activity
     *  /param renderer reference to the graphics renderer
     *  /return returns a mutableList of platforms
     */
    fun generateLevel(screenWidth: Int, screenHeight: Int, numPlatforms : Int = 30) : MutableList<Platform> {
        val platforms = mutableListOf<Platform>()

        val doubleScreenHeight = screenHeight * 2

        // Generate random platforms
        val numberOfPredeterminedPlatform = numPlatforms/4*3
        val predeterminedGap = doubleScreenHeight / numberOfPredeterminedPlatform

        // predetermined platforms with fixed gap 3/4 of loaded platforms
        for (i in 0..numberOfPredeterminedPlatform) {
            val x = (0..screenWidth).random().toFloat()
            val y = (predeterminedGap * i).toFloat()
            val platform = Platform(x, y)
            platforms.add(platform)
        }

        // random platforms
        for (i in (numberOfPredeterminedPlatform+1) until numPlatforms) {
            val x = (0..screenWidth).random().toFloat()
            val y = (0..doubleScreenHeight).random().toFloat()
            val platform = Platform(x, y)
            platforms.add(platform)
        }

        // Return the game objects as an array
        return platforms.toMutableList()
    }
}