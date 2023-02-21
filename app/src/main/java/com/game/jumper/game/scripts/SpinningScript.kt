package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script

class SpinningScript : Script() {
    override fun start() {
        Log.d("SpinningScript", "Started!")
    }

    override fun update() {
        super.update()
        gameObject.transform.rotation += 1f
    }
}