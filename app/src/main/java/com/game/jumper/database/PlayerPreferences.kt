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

    fun getPrefId(): Int {
        return sharedPreferences.getInt(PreferencesKeys._ID, 0)
    }

    fun updatePrefId(_id : Int) {
        sharedPreferences.edit().putInt(PreferencesKeys._ID, _id).apply()
    }

    fun getPrefImage(): Int {
        return sharedPreferences.getInt(PreferencesKeys._IMAGE, 0)
    }

    fun updatePrefImage(_image : Int) {
        sharedPreferences.edit().putInt(PreferencesKeys._IMAGE, _image).apply()
    }

    fun getPrefDesc(): String? {
        return sharedPreferences.getString(PreferencesKeys._DESC, " ")
    }

    fun updatePrefDesc(_desc : String) {
        sharedPreferences.edit().putString(PreferencesKeys._DESC, _desc).apply()
    }
    fun getPrefFp(): String? {
        return sharedPreferences.getString(PreferencesKeys._DESC, " ")
    }

    fun updatePrefFp(_desc : String) {
        sharedPreferences.edit().putString(PreferencesKeys._DESC, _desc).apply()
    }

    companion object {
        private const val PLAYER_PREFERENCES = "player_preferences"

        @Volatile
        private var INSTANCE: PlayerPreferences? = null

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