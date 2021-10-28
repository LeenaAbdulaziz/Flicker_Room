package com.example.flicker_room

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flicker_room.API.Photo
import kotlinx.android.synthetic.main.item.view.*


//import kotlinx.android.synthetic.main.single_item.view.*

class RVAdapter( private val names: ArrayList<Photo>) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
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
            val link =
                "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            Glide.with(holder.itemView.context)
                .load(link)
                .into(imageView2)
            clItr.setOnClickListener {

                val info = Intent(holder.itemView.context, fullview::class.java)
                info.putExtra("title", photo.title)
                info.putExtra("link", link)
                holder.itemView.context.startActivity(info)

            }

        }
    }


    override fun getItemCount() = names.size

}
