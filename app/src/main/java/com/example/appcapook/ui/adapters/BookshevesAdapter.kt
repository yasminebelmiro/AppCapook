package com.example.appcapook
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookshevesAdapter (private val books: List<Book>) :
    RecyclerView.Adapter<BookshevesAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView5)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val textView: TextView = view.findViewById(R.id.progressText)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.livros_estantes_layout, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = books[position]
        holder.imageView.setImageResource(item.img)
        holder.progressBar.progress = item.readingProgress
        holder.textView.text = "${item.readingProgress}%"


    }

    override fun getItemCount(): Int = books.size
}