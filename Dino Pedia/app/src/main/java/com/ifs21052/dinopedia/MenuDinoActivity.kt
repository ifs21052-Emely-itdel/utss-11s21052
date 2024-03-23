package com.ifs21052.dinopedia

import ListDino
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21052.dinopedia.databinding.ActivityMenuDinoBinding

class MenuDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuDinoBinding
    private val dataListDinos = ArrayList<ListDino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinos.setHasFixedSize(false)
        dataListDinos.addAll(getListDinos())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<ListDino> {
        val dataName =
            resources.getStringArray(R.array.namaDino)
        val dataIcon =
            resources.obtainTypedArray(R.array.gbrDino)
        val dataDescription =
            resources.getStringArray(R.array.descDino)
        val dataKelompok =
            resources.getStringArray(R.array.kelDino)
        val dataHabitat =
            resources.getStringArray(R.array.habitatDino)
        val dataMakanan =
            resources.getStringArray(R.array.makananDino)
        val dataPanjang =
            resources.getStringArray(R.array.panjangDino)
        val dataTinggi =
            resources.getStringArray(R.array.tinggiDino)
        val dataBobot =
            resources.getStringArray(R.array.bobotDino)
        val dataKelemahan =
            resources.getStringArray(R.array.kelemahanDino)

        val MenuDino = ArrayList<ListDino>()
        for (i in dataName.indices) {
            val listDino = ListDino(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],dataKelompok[i],
                dataHabitat[i], dataMakanan[i], dataPanjang[i], dataTinggi[i], dataBobot[i], dataKelemahan[i])
            MenuDino.add(listDino)
        }
        return MenuDino
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

        val listDinoAdapter = ListDinoAdapter(dataListDinos)
        binding.rvDinos.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListDino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(listDino: ListDino) {
        val intentWithData = Intent(this@MenuDinoActivity,
            DetailDinoActivity::class.java)
        intentWithData.putExtra(DetailDinoActivity.EXTRA_LISTDINO, listDino)
        startActivity(intentWithData)
    }


}
