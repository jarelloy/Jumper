/*!*****************************************************************************
\file			InstructionActivity.kt
\author			Jarell Ow Yong
\par DP email:	o.yongjarell@digipen.edu
\par Course:	CSD3156
\par Project:	Jumper Game
\date			25/2/2023
\brief

This file contains the function definition used in Jumper for InstructionActivity

\copyright	Copyright (C) 2021 DigiPen Institute of Technology.
			Reproduction or disclosure of this file or its contents without the
			prior written consent of DigiPen Institute of Technology is prohibited.
*******************************************************************************/

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

    /*
    onCreate function override to be called when first starting the activity
    This function uses the activity_instruction.xml file for the necessary layout.
    This function also teaches the users how to play the game via the instructions
     */
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