package com.game.jumper.game.scripts

import android.content.Context
import android.util.Log
import com.game.jumper.engine.objects.Script

class PlatformScript : Script() {
    override fun start() {
        Log.d("PlatformScript", "Started!")
    }
    override fun update() {
        super.update()

        val gravity : Float = 0.05f

        gameObject.transform.position.y += gravity


    }
}