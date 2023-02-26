/*************************************************************************
\file   PlatformScript.kt
\author Jeremiah Lim Eng Keng, 2002408
\date   Feb 15, 2023
\brief  This file contains the implementation for the PlatformScript
 *************************************************************************/
package com.game.jumper.game.scripts
import android.util.Log
import com.game.jumper.engine.objects.Script

class PlatformScript : Script() {

    companion object {
        /**
         * A check for if a platform has been jumped upon
         */
        var jumped : Boolean = false
    }
    override fun start() {
        Log.d("PlatformScript", "Started!")
    }
    override fun update() {
        super.update()
    }
}