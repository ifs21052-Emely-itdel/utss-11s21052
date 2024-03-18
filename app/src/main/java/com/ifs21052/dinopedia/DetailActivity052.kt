package com.ifs21052.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21052.dinopedia.databinding.ActivityDetail052Binding


class  DetailActivity052 : AppCompatActivity() {
    private lateinit var binding: ActivityDetail052Binding
    private var dino: Dino? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail052Binding.inflate(layoutInflater)
        setContentView(binding.root)
        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,
                Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Buah ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }
    private fun loadData(dino: Dino) {
        binding.ivDetailIcon.setImageResource(dino.icon)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.description
        binding.tvDetailCharacteristic.text = dino.characteristic
        binding.tvDetailPositive.text = dino.habitat
        binding.tvDetailNegative.text = dino.peilaku
        binding.tvDetailPeriode.text = dino.periode
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