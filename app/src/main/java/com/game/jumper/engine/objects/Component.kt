package com.game.jumper.engine.objects

import com.game.jumper.engine.GameObject
import com.game.jumper.engine.Scene
import com.game.jumper.math.Transform

fun interface Updatable {
    fun update()
}

open class Component : Updatable{
    lateinit var gameObject: GameObject

    var transform: Transform
        get() = gameObject.transform
        set(value) {gameObject.transform = value}

    fun createObject() : GameObject{
        return Scene.activeScene.instantiateObject()
    }

    fun getObjectByName(name: String) : GameObject{
        return Scene.activeScene.findObject(name)
    }

    override fun update() {}

    open fun pausedUpdate(){}

    fun destroy() {
        gameObject.destroyComponent(this)
    }

}