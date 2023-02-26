/*************************************************************************
\file   HatScript.kt
\author Jeremiah Lim Eng Keng, 2002408
\date   Feb 15, 2023
\brief  This file contains the implementation for the HatScript
 *************************************************************************/

package com.game.jumper.game.scripts

import android.util.Log
import com.game.jumper.engine.objects.Script

class HatScript : Script() {
    companion object {
        /**
         * used for checking for the kid hat
         */
        var hat : Boolean = false
    }

    override fun start() {
        //gyroscopeRotationTracker.start()
        Log.d("HatScript", "Started!")
    }

    override fun update() {
        super.update()

        // hat will update based on the position of the playerScript
        gameObject.transform.position.x = PlayerScript.position.x
        // kid hat requires different scaling and y transformation
        if (hat) {
            gameObject.transform.scale.x = 1f
            gameObject.transform.scale.y = 1f
            gameObject.transform.position.y = PlayerScript.position.y + 1f
        }
        else
            gameObject.transform.position.y = PlayerScript.position.y + 0.7f
    }
}