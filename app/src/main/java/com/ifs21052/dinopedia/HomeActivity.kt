package com.ifs21052.dinopedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ifs21052.dinopedia.MainActivity
import com.ifs21052.dinopedia.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Memberi aksi klik pada tombol "Explore Films"
        binding.exploreButton.setOnClickListener {
            // Memulai MainActivity
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
        }
    }
}
