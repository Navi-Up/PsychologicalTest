package com.example.androidproject.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.databinding.ActivityMainBinding
import com.example.androidproject.ui.character.CharacterTestActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCharacterTest.setOnClickListener {
            val intent = Intent(this, CharacterTestActivity::class.java)
            startActivity(intent)
        }
    }
}
