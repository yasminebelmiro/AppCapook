package com.example.appcapook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.model.Volume
import com.squareup.picasso.Picasso

class BookAdapter( val items: List<Volume>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return BookViewHolder(view)
    }
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val volume = items[position]
        if (volume.volumeInfo.imageLinks?.smallThumbnail != null) {
            Picasso.get().load(volume.volumeInfo.imageLinks.smallThumbnail).into(holder.imageView)
        } else{
            holder.imageView.setImageResource(R.drawable.baseline_image_24)
            }
        }
    }

