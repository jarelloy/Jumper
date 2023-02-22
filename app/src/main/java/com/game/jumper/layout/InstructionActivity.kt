package com.game.jumper.layout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.game.jumper.MainActivity
import com.game.jumper.databinding.ActivityInstructionBinding
import com.game.jumper.R

class InstructionActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInstructionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstructionBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_instruction)

        val instructionbackBtn : Button = findViewById(R.id.instructionbackBtn)

        instructionbackBtn.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}