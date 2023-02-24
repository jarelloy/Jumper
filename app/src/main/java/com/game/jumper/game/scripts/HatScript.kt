package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.GameObject
import com.game.jumper.engine.objects.Script
import com.game.jumper.graphics.JumperGLRenderer
import com.game.jumper.game.scripts.PlayerScript

class HatScript : Script() {

    companion object {
        var hat : Int = 0
    }

    override fun start() {
        //gyroscopeRotationTracker.start()
        Log.d("HatScript", "Started!")
    }

    override fun update() {
        super.update()

        gameObject.transform.position.x = PlayerScript.position.x
        if (hat == 2) {
            gameObject.transform.position.y = PlayerScript.position.y + 1f
        }
        else
            gameObject.transform.position.y = PlayerScript.position.y + 0.4f
        //Log.d("HatPos","x: ${ gameObject.transform.position.x}, y: ${ gameObject.transform.position.y}")
    }
}