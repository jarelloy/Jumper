package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script

class PlatformScript : Script() {
    override fun start() {
        Log.d("PlatformScript", "Started!")
    }
    override fun update() {
        super.update()
        gameObject.transform.position.y -= .005f
        if (gameObject.transform.position.y < -1.2)
            gameObject.destroy()

    }
}