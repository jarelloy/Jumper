package com.game.jumper.game

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.game.jumper.engine.GameObject
import com.game.jumper.engine.Scene
import com.game.jumper.game.scripts.SpinningScript
import com.game.jumper.graphics.JumperQuad
import com.game.jumper.level.LevelGenerator
import com.game.jumper.level.Platform

class SampleScene(context: Context) : Scene(context) {
    private var object1 : GameObject
    private var platform: Array<Platform>

    init {
        val width: Int = Resources.getSystem().displayMetrics.widthPixels
        val height: Int = Resources.getSystem().displayMetrics.heightPixels

        gameObjects.clear()
        paused = false

        object1 = createNewObject()
         object1.name = "Spinning Object"
        object1.transform.position.x = 0f
        object1.transform.scale.x = 100f
        object1.transform.scale.y = 100f
        object1.addScript<SpinningScript>()
        val quad1 = JumperQuad(context, "art/BPlayer_Idle.png")
        object1.quad = quad1

        platform = LevelGenerator().generateLevel(width, height)

        for (i in platform.indices) {
            Log.d("Platform","$i, x: ${platform[i].x}, y: ${platform[i].y}" )

            /*object1 = createNewObject()
            object1.name = "Platform $i"
            object1.transform.position.x = platform[i].x.toFloat()
            object1.transform.position.y = platform[i].y.toFloat()
            object1.transform.scale.x = 10f
            object1.transform.scale.y = 10f
            object1.quad*/
        }
    }

    override fun update() {
        super.update()


    }
}