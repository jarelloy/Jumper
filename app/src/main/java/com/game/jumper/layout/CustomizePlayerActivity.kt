/*!*****************************************************************************
\file			CustomizePlayerActivity.kt
\author			Jarell Ow Yong
\par DP email:	o.yongjarell@digipen.edu
\par Course:	CSD3156
\par Project:	Jumper Game
\date			25/2/2023
\brief

This file contains the function definition used in Jumper for CustomizePlayerActivity

\copyright	Copyright (C) 2021 DigiPen Institute of Technology.
			Reproduction or disclosure of this file or its contents without the
			prior written consent of DigiPen Institute of Technology is prohibited.
*******************************************************************************/

package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.MainActivity
import com.game.jumper.R
import com.game.jumper.database.PlayerPreferences
import com.game.jumper.database.entity.PowerUp
import com.game.jumper.databinding.ActivityCustomizePlayerBinding
import com.game.jumper.model.PowerUpAdapter
import com.game.jumper.model.PowerUpViewModel
import kotlin.system.exitProcess

class CustomizePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizePlayerBinding
    private lateinit var powerUpViewModel: PowerUpViewModel
    private lateinit var powerUpAdapter : PowerUpAdapter
    private var powerUpData  : List<PowerUp> = emptyList()
    companion object{
        var chosenPowerUp : PowerUp? = null
    }

    /*
    onCreate function override to be called when first starting the activity
    This function uses the activity_customize_player.xml file for the necessary layout.
    This function also calls for the database and allows user to set the player's cosmetic before
    the game starts
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizePlayerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_customize_player)

        // initialize all buttons and recycler view and database
        val customizebackBtn : Button = findViewById(R.id.customizebackBtn)
        val imageSelected : ImageView = findViewById(R.id.selectedImage)

        val recyclerView = findViewById<RecyclerView>(R.id.customizationRecycleView)
        powerUpAdapter = PowerUpAdapter()
        powerUpViewModel = ViewModelProvider(this).get(PowerUpViewModel::class.java)
        powerUpViewModel.powerUps.observe(this, Observer { entry ->
            Log.d("CustomizePlayerActivity", "Received data from database: $entry")
            powerUpAdapter.setData(entry)
        })
        recyclerView.adapter = powerUpAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        customizebackBtn.setOnClickListener {
            finish()
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        powerUpViewModel.powerUps.observe(this, Observer { powerUps ->
            if (powerUps.isEmpty()) {
                // Database is empty, insert initial data
                val powerUps = loadPowerUps()
                loadPowerUpToDatabase(powerUps)
            }
            // Database has data, update the adapter
            powerUpAdapter.setData(powerUps)
        })
        powerUpData = loadPowerUps()

        val playerPreferences = PlayerPreferences.getInstance(this)
        if(playerPreferences.getPrefId() != null && playerPreferences.getPrefImage() != null
            && playerPreferences.getPrefDesc() != null) {
                chosenPowerUp = playerPreferences.getPrefDesc()
                    ?.let {
                        PowerUp(
                            playerPreferences.getPrefId(),
                            playerPreferences.getPrefImage(),
                            it,
                            playerPreferences.getPrefFp()!!
                        )
                    }
                //chosenPowerUp?.let { imageSelected.setImageResource(it.image) }
        }

        //when clicked, the image will appear at the top showing users what has been selected,
        //this will also confirms the user's selection and will be shown on the player when
        //the game starts
        powerUpAdapter.setOnItemClickListener(object : PowerUpAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                imageSelected.setImageResource(powerUpData[position].image)
                chosenPowerUp = powerUpData[position]
                chosenPowerUp!!.id = position
                playerPreferences.updatePrefId(chosenPowerUp!!.id)
                playerPreferences.updatePrefImage(chosenPowerUp!!.image)
                playerPreferences.updatePrefDesc(chosenPowerUp!!.description)
            }
        })

    }

    //Functions gives the player power ups according to what was selected
    fun loadPowerUpToDatabase(list : List<PowerUp> ) {
        for (i in list.indices)
        {
            val entry = PowerUp(list[i].id, list[i].image, list[i].description, list[i].filepath)
            powerUpViewModel?.insertPowerUp(entry)
            Log.d("Customize", list[i].image.toString())
            Log.d("Customize", list[i].description)
        }
    }

    //Function shows the list of power ups available for user to select
    fun loadPowerUps(): List<PowerUp>
    {
        return listOf<PowerUp>(
            PowerUp(0, R.drawable.hat1, "Brella, it is fo’ drizzle.", "art/hat1.png"),
            PowerUp(0, R.drawable.hat2, "You need a crown.", "art/hat2.png"),
            PowerUp(0, R.drawable.hat3, "Hat’s how we roll.", "art/hat3.png"),
            PowerUp(0, R.drawable.hat4, "Leaf me alone.", "art/hat4.png"),
            PowerUp(0, R.drawable.hat5, "My Kid.", "art/BPlayer_Idle.png")
        )
    }
}