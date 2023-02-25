package com.game.jumper.database

/*************************************************************************
    \file   PlayerPreferences.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of a class for PlayerPreferences
 *************************************************************************/
import android.content.Context
/*************************************************************************
 *   /brief  A class for PlayerPreferences
 *************************************************************************/
class PlayerPreferences (context: Context) {
    private val sharedPreferences = context.applicationContext.getSharedPreferences(PLAYER_PREFERENCES, Context.MODE_PRIVATE)
    private object PreferencesKeys {
        const val _ID = "powerUpId"
        const val _IMAGE = "powerUpImage"
        const val _DESC = "powerUpDesc"
        const val _FP = "powerUpFp"
    }
    /*************************************************************************
     *   /brief  This function returns the shared preference id
     *************************************************************************/
    fun getPrefId(): Int {
        return sharedPreferences.getInt(PreferencesKeys._ID, 0)
    }
    /*************************************************************************
     *   /brief  This function updates the shared preference id
     *************************************************************************/
    fun updatePrefId(_id : Int) {
        sharedPreferences.edit().putInt(PreferencesKeys._ID, _id).apply()
    }
    /*************************************************************************
     *   /brief  This function returns the shared preference image id
     *************************************************************************/
    fun getPrefImage(): Int {
        return sharedPreferences.getInt(PreferencesKeys._IMAGE, 0)
    }
    /*************************************************************************
     *   /brief  This function updates the shared preference image id
     *************************************************************************/
    fun updatePrefImage(_image : Int) {
        sharedPreferences.edit().putInt(PreferencesKeys._IMAGE, _image).apply()
    }
    /*************************************************************************
     *   /brief  This function returns the shared preference description
     *************************************************************************/
    fun getPrefDesc(): String? {
        return sharedPreferences.getString(PreferencesKeys._DESC, " ")
    }
    /*************************************************************************
     *   /brief  This function updates the shared preference description
     *************************************************************************/
    fun updatePrefDesc(_desc : String) {
        sharedPreferences.edit().putString(PreferencesKeys._DESC, _desc).apply()
    }
    /*************************************************************************
     *   /brief  This function returns the shared preference filepath
     *************************************************************************/
    fun getPrefFp(): String? {
        return sharedPreferences.getString(PreferencesKeys._FP, " ")
    }
    /*************************************************************************
     *   /brief  This function updates the shared preference filepath
     *************************************************************************/
    fun updatePrefFp(_desc : String) {
        sharedPreferences.edit().putString(PreferencesKeys._FP, _desc).apply()
    }

    companion object {
        private const val PLAYER_PREFERENCES = "player_preferences"

        @Volatile
        private var INSTANCE: PlayerPreferences? = null

        /*************************************************************************
         *   /brief  This function returns the instance of the datastore preference
         *************************************************************************/
        fun getInstance(context: Context): PlayerPreferences {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = PlayerPreferences(context)
                INSTANCE = instance
                instance
            }
        }
    }
}