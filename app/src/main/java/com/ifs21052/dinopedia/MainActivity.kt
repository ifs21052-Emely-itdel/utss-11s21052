package com.ifs21052.dinopedia;

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21052.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataDinos = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinos.setHasFixedSize(false)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dino> {
        val dataName =
            resources.getStringArray(R.array.dinofams_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dinos_icon)
        val dataDescription =
            resources.getStringArray(R.array.dinos_description)
        val dataPeriode =
            resources.getStringArray(R.array.dinos_periode)
        val dataCharacteristic =
            resources.getStringArray(R.array.dinos_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.dinos_habitat)
        val dataLingkungan =
            resources.getStringArray(R.array.dinos_lingkungan)
        val dataPerilaku =
            resources.getStringArray(R.array.dinos_perilaku)
        val dataKlasifikasi =
            resources.getStringArray(R.array.dinos_klasifikasi)
        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],dataPeriode[i],
                dataCharacteristic[i], dataHabitat[i], dataLingkungan[i], dataPerilaku[i], dataKlasifikasi[i])
            listDino.add(dino)
        }
        return listDino
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = ListDinoAdapter(dataDinos)
        binding.rvDinos.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity052::class.java)
        intentWithData.putExtra(DetailActivity052.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_toast -> showToast()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun showToast() {
        Toast.makeText(
            this, "Maaf, menu belum sempat dibuat",
            Toast.LENGTH_SHORT
        ).show()
    }
}