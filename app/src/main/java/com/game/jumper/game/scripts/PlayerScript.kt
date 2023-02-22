package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script
import com.game.jumper.math.Vector2
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.motionSensor.MotionSensorListener
import com.game.jumper.engine.GameLoopGl

class PlayerScript  : Script() {

    var gravity : Float = -0.05f
    var position : Vector2 = Vector2(0.0f, 0.1f)
    var velocity : Vector2 = Vector2(0f, 0f )

    override fun start() {
        //gyroscopeRotationTracker.start()
        Log.d("PlayerScript", "Started!")
    }
    override fun update() {
        super.update()

        velocity = Vector2( MotionSensorListener.currentRoll, 0f)

        gameObject.transform.position.y += gravity

        if (velocity.x > 20)
            gameObject.transform.position.x -= 0.2f
        else if (velocity.x > 10)
            gameObject.transform.position.x -= 0.1f
        else if (velocity.x > 2)
            gameObject.transform.position.x -= 0.01f

        if (velocity.x < -20)
            gameObject.transform.position.x += 0.2f
        else if (velocity.x < -10)
            gameObject.transform.position.x += 0.1f
        else if (velocity.x < -2)
            gameObject.transform.position.x += 0.01f

        gameObject.transform.position.x += velocity.x * GameLoopGl.deltaTime.toFloat()

        JumperGLRenderer.setCamPos(position.x, position.y)

    }
}