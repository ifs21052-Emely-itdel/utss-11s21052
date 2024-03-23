package com.ifs21052.dinopedia

import ListDino
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21052.dinopedia.databinding.ActivityDetailDinoBinding


class  DetailDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinoBinding
    private var listDino: ListDino? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listDino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_LISTDINO,
                ListDino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_LISTDINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (listDino != null) {
            supportActionBar?.title = "Detail ${listDino!!.name}"
            loadData(listDino!!)
        } else {
            finish()
        }
    }
    private fun loadData(listDino: ListDino) {
        binding.ivDetailIcon.setImageResource(listDino.icon)
        binding.tvDetailName.text = listDino.name
        binding.tvDetailDescription.text = listDino.description
        binding.tvDetailKel.text = listDino.kelompok
        binding.tvDetailHabitat.text = listDino.habitat
        binding.tvDetailMakanan.text = listDino.makanan
        binding.tvDetailPanjang.text = listDino.panjang
        binding.tvDetailTinggi.text = listDino.tinggi
        binding.tvDetailBobot.text = listDino.bobot
        binding.tvDetailKelemahan.text = listDino.kelemahan
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
        const val EXTRA_LISTDINO = "extra_dino"
    }
}