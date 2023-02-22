package com.game.jumper.game.scripts

import android.content.Context
import android.util.Log
import com.game.jumper.engine.objects.Script

class PlatformScript : Script() {
    companion object {
        var jumped : Boolean = false
    }
    override fun start() {
        Log.d("PlatformScript", "Started!")
    }
    override fun update() {
        super.update()
    }
}