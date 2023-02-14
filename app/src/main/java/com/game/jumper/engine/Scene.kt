package com.game.jumper.engine

import com.game.jumper.math.Vector2

open class Scene(var view: GameGl) {
    protected var gameObjects = ArrayList<GameObject>()

    /** Touch control handler **/
    companion object {
        var touched : Boolean = false
        var touchPos: Vector2 = Vector2()
    }

    init {

    }

    fun start() {
        for(i in 0 until gameObjects.count())
            gameObjects[i].start()
    }

    open fun update() {
        gameObjects.forEach{gameObject -> gameObject.update()}
    }

    open fun draw() {
        gameObjects.forEach{gameObject -> gameObject.draw() }
    }
}