/*************************************************************************
\file   Component.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the implementation of a component base class
 *************************************************************************/
package com.game.jumper.engine.objects

import com.game.jumper.engine.GameObject
import com.game.jumper.engine.Scene
import com.game.jumper.math.Transform

/**
 * Interface of an updatable class
 */
fun interface Updatable {
    fun update()
}

/**
 * Component class
 *
 * This class assumes that there is 1 scene in the entire app using [Scene.activeScene]
 */
open class Component : Updatable{
    lateinit var gameObject: GameObject

    var transform: Transform
        get() = gameObject.transform
        set(value) {gameObject.transform = value}

    /**
     * Create an object by instantiating one from the scene
     */
    fun createObject() : GameObject{
        return Scene.activeScene.instantiateObject()
    }

    /**
     * Get the object attached to the
     */
    fun getObjectByName(name: String) : GameObject{
        return Scene.activeScene.findObject(name)
    }

    /**
     * Base function update for the component
     */
    override fun update() {}

    /**
     * Base function update for the component when the scene is paused
     */
    open fun pausedUpdate(){}

    /**
     * Destroy this component from the game object attached to it
     */
    fun destroy() {
        gameObject.destroyComponent(this)
    }

}