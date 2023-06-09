package com.divyansh.imagedownloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chaquo.python.PyObject
import com.divyansh.imagedownloader.Data.AdapterClass
import com.divyansh.imagedownloader.Data.Modal
import com.divyansh.imagedownloader.databinding.ActivityImageDisplayBinding

class ImageDisplay : AppCompatActivity() {
    private lateinit var binding : ActivityImageDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recycler
        var layoutManager = GridLayoutManager(applicationContext , 2 , LinearLayoutManager.VERTICAL , false)
        recyclerView.layoutManager = layoutManager

        var data_list: ArrayList<String> = ArrayList<String>()
        var res = intent.getStringArrayListExtra("dimage")


        if (res != null) {
            for (i in res){
                if(i!=null)
                    data_list.add(i)
            }
        }
        var adapter = AdapterClass(data_list , this )
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

}