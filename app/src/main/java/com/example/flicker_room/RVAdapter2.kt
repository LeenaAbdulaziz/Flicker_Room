package com.example.flicker_room

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flicker_room.data.Flicker
import com.example.flicker_room.data.FlickerDatabase
import kotlinx.android.synthetic.main.item.view.*


//import kotlinx.android.synthetic.main.single_item.view.*

class RVAdapter2(val activity: Favimg, val names: List<Flicker>) : RecyclerView.Adapter<RVAdapter2.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item, parent, false
            )
        )

    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = names[position]


        holder.itemView.apply {
            tltle.text = photo.title

            Glide.with(holder.itemView.context)
                .load(photo.link)
                .into(imageView2)
            clItr.setOnClickListener {

                activity.confirm(photo)

            }
//            imageView2.setOnClickListener {
//
//            }


        }
    }


    override fun getItemCount() = names.size





}

