package com.example.appcapook
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookReadingAdapter (private val books: List<Book>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_book_layout, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_book_layout, parent, false)
            BookViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            val book = books[position - 1] // Ajuste porque o header ocupa a posição 0
            holder.imageView.setImageResource(book.img)
            holder.titleTextView.text = book.title
            holder.pageCountTextView.text = "Páginas: ${book.pageCount}"
            holder.progressTextView.text = "Progresso: ${book.readingProgress}%"
        }
    }

    override fun getItemCount(): Int {
        return books.size + 1 // Inclui o layout do cabeçalho
    }

    // ViewHolder para o cabeçalho
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // ViewHolder para os itens normais
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView4)
        val titleTextView: TextView = itemView.findViewById(R.id.textView2)
        val pageCountTextView: TextView = itemView.findViewById(R.id.textView3)
        val progressTextView: TextView = itemView.findViewById(R.id.textView4)
    }
}