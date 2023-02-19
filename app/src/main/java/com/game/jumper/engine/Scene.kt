package com.game.jumper.engine

import android.content.Context
import com.game.jumper.math.Vector2

open class Scene(context: Context) {
    protected var gameObjects = ArrayList<GameObject>()
    var paused = false

    /** Touch control handler **/
    companion object {
        var touched : Boolean = false
        var touchPos: Vector2 = Vector2()
        lateinit var activeScene: Scene
            private set
    }

    init {

    }

    //
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

    // Create object that has started
    fun instantiateObject(): GameObject{
        val gameObject = createNewObject()
        gameObject.start()
        return gameObject
    }

    fun findObject(name: String) : GameObject{
        return gameObjects.first{ gameObject -> gameObject.name == name }
    }

    fun start() {
        activeScene = this
        for(i in 0 until gameObjects.count())
            gameObjects[i].start()
    }

    open fun update() {
        if (!paused)
            gameObjects.forEach{gameObject -> gameObject.update()}
    }

    open fun draw() {
        gameObjects.forEach{gameObject -> gameObject.draw() }
    }
}