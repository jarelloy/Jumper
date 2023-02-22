package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script
import com.game.jumper.math.Vector2
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.motionSensor.MotionSensorListener
import com.game.jumper.engine.GameLoopGl

class PlayerScript  : Script() {
    private val gravity : Float = -1f
    private val jump : Float = 5f
    companion object {
        //var position : Vector2 = Vector2(0.0f, 0.1f)
        var velocity : Vector2 = Vector2(0f, 0f )
    }

    override fun start() {
        //gyroscopeRotationTracker.start()
        Log.d("PlayerScript", "Started!")
    }
    override fun update() {
        super.update()
        val dt = GameLoopGl.deltaTime.toFloat()
        var rotation = MotionSensorListener.currentRotation.toFloat()

        // Gravity
        //velocity.y = gravity

        // Change velocity using the rotation of the phone
        if (rotation > 45)
            velocity.x -= 0.1f
        else if (rotation > 20)
            velocity.x -= 0.05f
        else if (rotation > 5)
            velocity.x -= 0.001f

        if (rotation < -45)
            velocity.x += 0.1f
        else if (rotation < -20)
            velocity.x += 0.05f
        else if (rotation < -5)
            velocity.x += 0.001f

        if (rotation <= 5 && rotation >= -5)
            velocity.x = 0f

        if (velocity.x < -1f)
            velocity.x = -1f

        if (velocity.x > 1f)
            velocity.x = 1f

        // wrapping of player
        if (gameObject.transform.position.x > 5f)
            gameObject.transform.position.x = -5f

        if (gameObject.transform.position.x < -5f)
            gameObject.transform.position.x = 5f

        velocity.y += gravity * dt

        // update the pos at the end
        gameObject.transform.position.x += velocity.x * dt
        gameObject.transform.position.y += velocity.y * dt

        JumperGLRenderer.setCamPos(0f, gameObject.transform.position.y)
        //Log.d("PlayerScript", "Rotation: ${rotation}, xPos: ${ gameObject.transform.position.x}, yPos: ${ gameObject.transform.position.y}, xVel: ${velocity.x}, yVel: ${velocity.y}")
    }
}