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
class CustomizePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizePlayerBinding
    private lateinit var powerUpViewModel: PowerUpViewModel
    private lateinit var powerUpAdapter : PowerUpAdapter
    private var powerUpData  : List<PowerUp> = emptyList()
    companion object{
        var chosenPowerUp : PowerUp? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizePlayerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_customize_player)

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
        chosenPowerUp = playerPreferences.getPrefDesc()
            ?.let { PowerUp(playerPreferences.getPrefId(), playerPreferences.getPrefImage(), it) }
        chosenPowerUp?.let { imageSelected.setImageResource(it.image) }

        powerUpAdapter.setOnItemClickListener(object : PowerUpAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                imageSelected.setImageResource(powerUpData[position].image)
                chosenPowerUp = powerUpData[position]
                playerPreferences.updatePrefId(chosenPowerUp!!.id)
                playerPreferences.updatePrefImage(chosenPowerUp!!.image)
                playerPreferences.updatePrefDesc(chosenPowerUp!!.description)
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        val recyclerView = findViewById<RecyclerView>(R.id.scoreboardRecycle)
//        val scrollPosition = recyclerView.layoutManager?.onSaveInstanceState()
//        outState.putParcelable("scroll_position", scrollPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
//        val recyclerView = findViewById<RecyclerView>(R.id.scoreboardRecycle)
//        val scrollPosition = savedInstanceState.getParcelable<Parcelable>("scroll_position")
//        recyclerView.layoutManager?.onRestoreInstanceState(scrollPosition)
    }

    fun loadPowerUpToDatabase(list : List<PowerUp> ) {
        for (i in list.indices)
        {
            val entry = PowerUp(list[i].id, list[i].image, list[i].description)
            powerUpViewModel?.insertPowerUp(entry)
            Log.d("Customize", list[i].image.toString())
            Log.d("Customize", list[i].description)
        }
    }

    fun loadPowerUps(): List<PowerUp>
    {
        return listOf<PowerUp>(
            PowerUp(0, R.drawable.hat, "Idk what to put here.."),
            PowerUp(0, R.drawable.hat2, "Looks cooler LOL")
        )
    }

}