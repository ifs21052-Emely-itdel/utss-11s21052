package com.ifs21052.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ifs21052.dinopedia.databinding.ActivityDetail052Binding


class  DetailActivity052 : AppCompatActivity() {
    private lateinit var binding: ActivityDetail052Binding
    private var famDino: FamDino? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail052Binding.inflate(layoutInflater)
        setContentView(binding.root)
        famDino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,
                FamDino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famDino != null) {
            supportActionBar?.title = "Detail ${famDino!!.name}"
            loadData(famDino!!)
        } else {
            finish()
        }

        val dinoBtn = findViewById<TextView>(R.id.dinoBtn)
        dinoBtn.setOnClickListener({
            val intent = Intent(this, ListDinoActivity::class.java)
            startActivity(intent)
        })

        binding.dinoBtn.setOnClickListener{
            val intentWithData = Intent(this@DetailActivity052, ListDinoActivity::class.java)
            intentWithData.putExtra(ListDinoActivity.EXTRA_FAMILI, famDino!!)
            startActivity(intentWithData)
        }
    }
    private fun loadData(famDino: FamDino) {
        binding.ivDetailIcon.setImageResource(famDino.icon)
        binding.tvDetailName.text = famDino.name
        binding.tvDetailDescription.text = famDino.description
        binding.tvDetailCharacteristic.text = famDino.characteristic
        binding.tvDetailPositive.text = famDino.habitat
        binding.tvDetailNegative.text = famDino.perilaku
        binding.tvDetailPeriode.text = famDino.periode
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}