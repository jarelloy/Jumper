package com.game.jumper.game

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.game.jumper.engine.GameObject
import com.game.jumper.engine.Scene
import com.game.jumper.game.scripts.PlatformScript
import com.game.jumper.game.scripts.PlayerScript
import com.game.jumper.graphics.JumperQuad
import com.game.jumper.level.LevelGenerator
import com.game.jumper.level.Platform
import com.game.jumper.math.Vector2
import com.game.jumper.motionSensor.MotionSensorListener
import com.game.jumper.engine.GameLoopGl
import com.game.jumper.level.Player
import kotlin.math.sign

class SampleScene(context: Context) : Scene(context) {
    //private var object1 : GameObject
    private var levelObject : GameObject
    private var playerObj : GameObject

    private var platform: Array<Platform>
    private var numPlatform : Int = 30
    private var width : Int = 0
    private var height : Int = 0
    private var quad2 : JumperQuad

    private val gyroscopeRotationTracker = MotionSensorListener(context)
    private var currentRotation = gyroscopeRotationTracker.getCurrentRoll()

    private var score : Int = 0
    private var isJumping : Boolean = false
    private var time : Float = 0f

    private var isDie : Boolean = false

    init {
        gyroscopeRotationTracker.start()

        width = Resources.getSystem().displayMetrics.widthPixels
        height = Resources.getSystem().displayMetrics.heightPixels

        val quad1 = JumperQuad(context, "art/BPlayer_Idle.png")
        quad2 = JumperQuad(context, "art/platform.png")

        gameObjects.clear()
        paused = false

        /*object1 = createNewObject()
        object1.name = "Spinning Object"
        object1.transform.position.x = 0f
        object1.transform.position.y = 0f
        object1.transform.scale.x = 1.0f
        object1.transform.scale.y = 1.0f
        object1.addScript<SpinningScript>()
        val quad1 = JumperQuad(context, "art/BPlayer_Idle.png")
        object1.quad = quad1*/

        playerObj = createNewObject()
        playerObj.name = "Player"
        playerObj.transform.position.x = 0f
        playerObj.transform.position.y = -1f
        playerObj.transform.scale.x = 1.0f
        playerObj.transform.scale.y = 1.0f
        playerObj.addScript<PlayerScript>()

        playerObj.quad = quad1

        platform = LevelGenerator().generateLevel(width, height, numPlatform)

        // First platform
        levelObject = createNewObject()
        levelObject.name = "Platform"
        levelObject.transform.position.x = 0f
        levelObject.transform.position.y = -5f
        levelObject.transform.scale.x = 3f
        levelObject.transform.scale.y = 0.2f
        levelObject.addScript<PlatformScript>()
        levelObject.quad = quad2

        for (i in platform.indices) {
            levelObject = createNewObject()
            levelObject.name = "Platform"
            levelObject.transform.position.x = (platform[i].x.toFloat() / width  - .5f) * 10
            levelObject.transform.position.y = (platform[i].y.toFloat() / height - .5f) * 20

            Log.d("Platform","$i, x: ${levelObject.transform.position.x}, y: ${levelObject.transform.position.y }" )
            levelObject.transform.scale.x = platform[i].sizeX
            levelObject.transform.scale.y = platform[i].sizeY
            levelObject.addScript<PlatformScript>()
            levelObject.quad = quad2
        }
    }

    override fun update() {
        super.update()

        currentRotation = gyroscopeRotationTracker.getCurrentRoll()

        val player = findObject("Player")

        // Player movement with gyro
        //TODO currently works with Extended controls but not IRL
        /*
        if (currentRotation > 20)
            player.transform.position.x -= 0.2f
        else if (currentRotation > 10)
            player.transform.position.x -= 0.1f
        else if (currentRotation > 2)
            player.transform.position.x -= 0.01f

        if (currentRotation < -20)
            player.transform.position.x += 0.2f
        else if (currentRotation < -10)
            player.transform.position.x += 0.1f
        else if (currentRotation < -2)
            player.transform.position.x += 0.01f*/

        //Log.d("playerXPos", player.transform.position.x.toString())
        //Log.d("playerXPos", currentRotation.toString())

        val playerPos = Vector2(player.transform.position.x, player.transform.position.y)

        for (platform in gameObjects) {
            if (platform.name == "Platform") {

                val platformPos = Vector2(platform.transform.position.x, platform.transform.position.y)

                if (checkCollided(playerPos, platformPos)) {
                    score += 100
                    isJumping = true
                    isDie = false
                    for (platformLoop in gameObjects) {
                        if (platformLoop.name == "Platform") {
                            //platformLoop.transform.position.y -= 3f * GameLoopGl.deltaTime
                        }
                    }
                }

                if (platform.transform.position.y < -12) {

                    var highestPlatform = 0f
                    // loop to get highest platform
                    for (platformLoop in gameObjects) {
                        if (platformLoop.name == "Platform") {
                            if (highestPlatform < platformLoop.transform.position.y)
                                highestPlatform = platformLoop.transform.position.y
                        }
                    }
                    platform.transform.position.y = highestPlatform + (1..10 ).random() % 3
                }
            }
        }

        if (!isDie) {
            //time += GameLoopGl.deltaTime
            if (time > 1f)
                isDie = true
        }

        if (isDie) {

        }
    }
}

private fun checkCollided( playerPos : Vector2, platformPos : Vector2) : Boolean {
    val playerVector = Vector2(playerPos.x - platformPos.x, playerPos.y - platformPos.y - .5f)
    val upVector = Vector2(0f,1f)

    val dot = upVector.dotProduct((playerVector))
    // check if it is above the platform first
    if ((playerPos.x < (platformPos.x + 1.5f) && playerPos.x > (platformPos.x - 1.5f)) && dot >= -1f) {
        //Log.d("CheckCollision", "Above")
        // check if it hits 0
        if(dot <= 0f && dot >= -6f) {
            //Log.d("CheckCollision", "Yes, $dot")
            return true
        }
    }
    return false
}

