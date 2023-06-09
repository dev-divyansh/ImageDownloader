package com.divyansh.imagedownloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.divyansh.imagedownloader.databinding.ActivityFullDisplayBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class FullDisplay : AppCompatActivity() {
    private lateinit var binding : ActivityFullDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = intent.getStringExtra("image_uri" )


        Picasso.get()
            .load(uri.toString())
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .into(binding.fullimageView)

        binding.downloadButton.setOnClickListener {
            val d = downloader(uri , this)
        }


    }
}