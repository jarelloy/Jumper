package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script
import com.game.jumper.motionSensor.MotionSensorListener

class PlayerScript  : Script() {

    override fun start() {
        //gyroscopeRotationTracker.start()
        Log.d("PlayerScript", "Started!")
    }
    override fun update() {
        super.update()


        if (gameObject.transform.position.x > 5.5f)
            gameObject.transform.position.x = -5f

        if (gameObject.transform.position.x < -5.5f)
            gameObject.transform.position.x = 5f

        if (gameObject.transform.position.y < -10f)
            gameObject.transform.position.y = 10f
    }
}