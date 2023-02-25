/*************************************************************************
\file   Scene.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of the scene base class
 *************************************************************************/

package com.game.jumper.engine

import android.content.Context
import android.graphics.Matrix
import com.game.jumper.math.Vector2

/**
 *  Scene base class
 *
 *  This class is the base class of a scene which contains game objects
 *
 *  @param context the context of the activity
 */
open class Scene(context: Context) {
    protected var gameObjects = ArrayList<GameObject>()
    protected val context = context
    var paused = false

    companion object {
        lateinit var activeScene: Scene
            private set
    }

    init {

    }

    /**
     * Create a new object with name [name]
     */
    fun createNewObject(name: String = "") : GameObject {
        return try {
            val gameObject = gameObjects.first { gameObject ->  !gameObject.isActive }
            gameObject.name = name
            gameObject
        }
        catch (e : Exception) {
            val gameObject = GameObject()
            gameObject.name = name
            if(!gameObjects.add(gameObject))
                throw Exception()
            gameObjects.last()
        }
    }

    /**
     * Create an object and start immediately
     */
    fun instantiateObject(): GameObject{
        val gameObject = createNewObject()
        gameObject.start()
        return gameObject
    }

    /**
     * Find an object with name [name]
     */
    fun findObject(name: String) : GameObject{
        return gameObjects.first{ gameObject -> gameObject.name == name }
    }

    /**
     * Base start function of the scene
     */
    open fun start() {
        activeScene = this
        for(i in 0 until gameObjects.count())
            gameObjects[i].start()
    }

    /**
     * Base update function of the scene to update every frame
     */
    open fun update() {
        if (!paused)
            gameObjects.forEach{gameObject -> gameObject.update()}
    }

    /**
     * Base draw function of the scene to draw every draw call
     */
    open fun draw(vpMat : FloatArray) {
        gameObjects.forEach{gameObject -> gameObject.draw(vpMat) }
    }
}