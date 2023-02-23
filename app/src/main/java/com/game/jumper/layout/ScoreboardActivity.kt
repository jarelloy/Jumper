package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.MainActivity
import com.game.jumper.R
import com.game.jumper.databinding.ActivityScoreboardBinding
import com.game.jumper.model.HighScoreAdapter
import com.game.jumper.model.HighScoreViewModel

class ScoreboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreboardBinding
    private lateinit var highScoreViewModel: HighScoreViewModel
    //val myDataset = ScoreboardDatabase().loadScore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreboardBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_scoreboard)

        val scoreboardbackBtn : Button = findViewById(R.id.scoreboardbackBtn)

        val recyclerView = findViewById<RecyclerView>(R.id.scoreboardRecycle)
        val highScoreAdapter = HighScoreAdapter()
        recyclerView.adapter = highScoreAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //val scoreAdapter = ScoreAdapter(myDataset)
        //recyclerView.adapter = scoreAdapter
        //recyclerView.layoutManager = LinearLayoutManager(this)
        highScoreViewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)
        highScoreViewModel.highScores.observe(this, Observer { entry ->
            highScoreAdapter.setData(entry)
        })

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