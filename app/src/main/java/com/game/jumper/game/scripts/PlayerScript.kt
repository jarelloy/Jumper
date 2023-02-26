/*************************************************************************
\file   PlayerScript.kt
\author Jeremiah Lim Eng Keng, 2002408
\date   Feb 15, 2023
\brief  This file contains the implementation for the PlayerScript
 *************************************************************************/
package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script
import com.game.jumper.math.Vector2
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.motionSensor.MotionSensorListener
import com.game.jumper.engine.GameLoopGl

class PlayerScript  : Script() {
    private val gravity : Float = -1f
    companion object {
        var position : Vector2 = Vector2(0.0f, 0.1f)
        var velocity : Vector2 = Vector2(0f, 0f )
    }

    override fun start() {
        Log.d("PlayerScript", "Started!")
    }
    override fun update() {
        super.update()
        val dt = GameLoopGl.deltaTime.toFloat()
        var rotation = MotionSensorListener.currentRotation.toFloat()

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

        // if rotation of device is "upright" do not rotate
        if (rotation <= 5 && rotation >= -5)
            velocity.x = 0f

        // Cap the maximum velocity to -1.5 to 1.5
        if (velocity.x < -1.5f)
            velocity.x = -1.5f

        if (velocity.x > 1.5f)
            velocity.x = 1.5f

        // wrapping of player
        if (gameObject.transform.position.x > 5f)
            gameObject.transform.position.x = -5f

        if (gameObject.transform.position.x < -5f)
            gameObject.transform.position.x = 5f

        velocity.y += gravity * dt

        // update the pos at the end
        gameObject.transform.position.x += velocity.x * dt
        gameObject.transform.position.y += velocity.y * dt

        position.x = gameObject.transform.position.x
        position.y = gameObject.transform.position.y

        JumperGLRenderer.setCamPos(0f, gameObject.transform.position.y)
    }
}