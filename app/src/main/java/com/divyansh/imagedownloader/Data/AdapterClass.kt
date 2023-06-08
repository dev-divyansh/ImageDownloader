package com.divyansh.imagedownloader.Data

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.divyansh.imagedownloader.Data.AdapterClass.viewHolder
import com.divyansh.imagedownloader.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class AdapterClass internal constructor(arrayList: ArrayList<String>, context: Context) :
    RecyclerView.Adapter<viewHolder>() {
    var arrayList: ArrayList<String> = ArrayList<String>()
    var context: Context

    init {
        this.arrayList = arrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.imagecard, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val uri = arrayList[position].toString()

        Picasso.get()
            .load(uri)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .centerCrop()
            .into(holder.Imageview)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Imageview: ImageView

        init {
            Imageview = itemView.findViewById(R.id.imagecardView)
        }
    }
}