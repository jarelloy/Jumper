/*************************************************************************
\file   Script.kt
\author Cruz Rolly Matthew Capiral, 2000798
\date   Feb 24, 2023
\brief  This file contains the script abstract class
 *************************************************************************/
package com.game.jumper.engine.objects

/**
 * Abstract class of a script that extends the component class
 */
abstract class Script : Component(){
    abstract fun start()
}