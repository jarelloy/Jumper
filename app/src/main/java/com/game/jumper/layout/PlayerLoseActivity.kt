package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.game.jumper.MainActivity
import com.game.jumper.R
import com.game.jumper.database.entity.HighScore
import com.game.jumper.databinding.ActivityLoseBinding
import com.game.jumper.model.HighScoreViewModel
import kotlin.system.exitProcess

class PlayerLoseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoseBinding
    private lateinit var highScoreViewModel : HighScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        highScoreViewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)

        binding.editscoretext.text = GameActivity.score.toString()

        binding.savehighscoreBtn.setOnClickListener {
            insertHighScoreToDatabase(binding.editPlayerName.text.toString(), GameActivity.score)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.mmBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun insertHighScoreToDatabase(userName: String, userScore: Int ) {
        val entry = HighScore(0, userName , userScore)
        highScoreViewModel.insertHighScore(entry)
        //Toast.makeText(this, "Successfully added!", Toast.LENGTH_SHORT).show()
        Log.d("GameActivity", userScore.toString())
    }
}