package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.MainActivity
import com.game.jumper.R
import com.game.jumper.databinding.ActivityCustomizePlayerBinding
import edu.singaporetech.iwsp.Cosmetic
import edu.singaporetech.iwsp.CosmeticAdapter
import edu.singaporetech.iwsp.CustomizationDatabase
class CustomizePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomizePlayerBinding
    val myDataset = CustomizationDatabase().loadCosmetic()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomizePlayerBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_customize_player)

        val customizebackBtn : Button = findViewById(R.id.customizebackBtn)
        val imageSelected : ImageView = findViewById(R.id.selectedImage)

        val recyclerView = findViewById<RecyclerView>(R.id.customizationRecycleView)
        val cosmeticAdapter = CosmeticAdapter(myDataset)
        recyclerView.adapter = cosmeticAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        customizebackBtn.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        cosmeticAdapter.setOnItemClickListener(object : CosmeticAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                imageSelected.setImageResource(myDataset[position].imageResource)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val recyclerView = findViewById<RecyclerView>(R.id.scoreboardRecycle)
        val scrollPosition = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable("scroll_position", scrollPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val recyclerView = findViewById<RecyclerView>(R.id.scoreboardRecycle)
        val scrollPosition = savedInstanceState.getParcelable<Parcelable>("scroll_position")
        recyclerView.layoutManager?.onRestoreInstanceState(scrollPosition)
    }
}