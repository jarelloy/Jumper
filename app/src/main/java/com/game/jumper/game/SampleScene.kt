package com.game.jumper.game

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.game.jumper.engine.GameObject
import com.game.jumper.engine.Scene
import com.game.jumper.game.scripts.PlatformScript
import com.game.jumper.game.scripts.SpinningScript
import com.game.jumper.graphics.JumperQuad
import com.game.jumper.level.LevelGenerator
import com.game.jumper.level.Platform

class SampleScene(context: Context) : Scene(context) {
    private var object1 : GameObject
    private var levelObject : GameObject
    private var platform: Array<Platform>
    private var numPlatform : Int = 10
    private var width : Int = 0
    private var height : Int = 0
    private var quad2 : JumperQuad
    init {
        width = Resources.getSystem().displayMetrics.widthPixels
        height = Resources.getSystem().displayMetrics.heightPixels

        quad2 = JumperQuad(context, "art/platform.png")

        gameObjects.clear()
        paused = false

        object1 = createNewObject()
        object1.name = "Spinning Object"
        object1.transform.position.x = 0f
        object1.transform.position.y = 0f
        object1.transform.scale.x = 0.2f
        object1.transform.scale.y = 0.2f
        object1.addScript<SpinningScript>()
        val quad1 = JumperQuad(context, "art/BPlayer_Idle.png")
        object1.quad = quad1

        platform = LevelGenerator().generateLevel(width, height, numPlatform)

        levelObject = createNewObject()

        for (i in platform.indices) {

            levelObject = createNewObject()
            levelObject.name = "Platform $i"
            levelObject.transform.position.x = platform[i].x.toFloat() / width - 0.5f
            levelObject.transform.position.y = platform[i].y.toFloat() / height - 0.5f

            Log.d("Platform","$i, x: ${levelObject.transform.position.x}, y: ${levelObject.transform.position.y }" )
            levelObject.transform.scale.x = 0.2f
            levelObject.transform.scale.y = 0.05f
            levelObject.addScript<PlatformScript>()

            levelObject.quad = quad2
        }
    }

    override fun update() {
        super.update()



    }
}