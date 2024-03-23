package com.ifs21052.dinopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val emelyProfImageView: ImageView = findViewById(R.id.emely_prof)
        emelyProfImageView.setOnClickListener {
            val intent = Intent(this, SeeProfileActivity::class.java)
            startActivity(intent)
            true
        }
    }
}