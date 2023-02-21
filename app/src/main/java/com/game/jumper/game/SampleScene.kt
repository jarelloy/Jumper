package com.game.jumper.game

import android.content.Context
import com.game.jumper.engine.GameObject
import com.game.jumper.engine.Scene
import com.game.jumper.game.scripts.SpinningScript
import com.game.jumper.graphics.JumperQuad

class SampleScene(context: Context) : Scene(context){
    private val object1 : GameObject
    init {
        gameObjects.clear()
        paused = false

        object1 = createNewObject()
        object1.name = "Spinning Object"
        object1.transform.position.x = 0f
        object1.transform.position.y = 0f
        object1.transform.scale.x = 1f
        object1.transform.scale.y = 1f
        object1.addScript<SpinningScript>()
        val quad1 = JumperQuad(context, "art/BPlayer_Idle.png")
        object1.quad = quad1
    }

    override fun update() {
        super.update()
    }
}