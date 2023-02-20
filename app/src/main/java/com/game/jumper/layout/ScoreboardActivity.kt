package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.MainActivity
import com.game.jumper.R
import com.game.jumper.databinding.ActivityScoreboardBinding
import edu.singaporetech.iwsp.Datasource
import edu.singaporetech.iwsp.ScoreAdapter

class ScoreboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreboardBinding
    val myDataset = Datasource().loadScore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreboardBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_scoreboard)

        val scoreboardbackBtn : Button = findViewById(R.id.scoreboardbackBtn)

        val recyclerView = findViewById<RecyclerView>(R.id.scoreboardRecycle)
        val scoreAdapter = ScoreAdapter(myDataset)
        recyclerView.adapter = scoreAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        scoreboardbackBtn.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
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