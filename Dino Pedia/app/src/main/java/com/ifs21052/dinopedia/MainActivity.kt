package com.ifs21052.dinopedia;

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.ListActivity
import android.content.Intent
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21052.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamDinos = ArrayList<FamDino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinos.setHasFixedSize(false)
        dataFamDinos.addAll(getListDinos())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<FamDino> {
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
        val dataPerilaku =
            resources.getStringArray(R.array.dinos_perilaku)
        val dataIndexStart = resources.getStringArray(R.array.start_dinos)
        val dataIndexEnd = resources.getStringArray(R.array.end_dinos)

        val listFamDino = ArrayList<FamDino>()
        for (i in dataName.indices) {
            val famDino = FamDino(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],dataPeriode[i],
                dataCharacteristic[i], dataHabitat[i], dataPerilaku[i], dataIndexStart[i].toInt(), dataIndexEnd[i].toInt())
            listFamDino.add(famDino)
        }
        return listFamDino
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

        val famDinoAdapter = FamDinoAdapter(dataFamDinos)
        binding.rvDinos.adapter = famDinoAdapter
        famDinoAdapter.setOnItemClickCallback(object :
            FamDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FamDino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(famDino: FamDino) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity052::class.java)
        intentWithData.putExtra(DetailActivity052.EXTRA_DINO, famDino)
        startActivity(intentWithData)
    }


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
            R.id.menu_baru -> {
                val intent = Intent(this, GambarDino::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_toast -> {
                val intent = Intent(this, MenuDinoActivity::class.java)
                startActivity(intent)
                true
            }
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