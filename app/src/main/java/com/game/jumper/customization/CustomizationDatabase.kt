package edu.singaporetech.iwsp

import com.game.jumper.R

class CustomizationDatabase {
    fun loadCosmetic(): List<Cosmetic> {
        return listOf<Cosmetic>(
            Cosmetic(R.drawable.hat, "Idk what to put here.."),
            Cosmetic(R.drawable.hat2, "Looks cooler LOL")
        )
    }
}

