package com.game.jumper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.game.jumper.databinding.ActivityMainBinding
import com.game.jumper.graphics.JumperGLSurfaceView
import com.game.jumper.engine.GameGl
import com.game.jumper.layout.CustomizePlayerActivity
import com.game.jumper.layout.GameActivity
import com.game.jumper.layout.InstructionActivity
import com.game.jumper.layout.ScoreboardActivity
import com.google.android.material.internal.ContextUtils.getActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var game : GameGl
//    private lateinit var glSurfaceView : JumperGLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        binding.playgameBtn.setOnClickListener {
            //game = GameGl(this)
            //glSurfaceView = JumperGLSurfaceView(this)
            //setContentView(game)

            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.howtoplayBtn.setOnClickListener {
            val intent = Intent (this, InstructionActivity::class.java)
            startActivity(intent)
        }

        binding.scoreboardBtn.setOnClickListener {
            val intent = Intent (this, ScoreboardActivity::class.java)
            startActivity(intent)
        }

        binding.customizeplayerBtn.setOnClickListener {
            val intent = Intent (this, CustomizePlayerActivity::class.java)
            startActivity(intent)
        }
        //setContentView(binding.root)

        binding.exitgameBtn.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}