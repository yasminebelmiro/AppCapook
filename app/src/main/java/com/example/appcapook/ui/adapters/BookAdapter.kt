package com.example.appcapook.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcapook.ui.views.BookDatailsActivity
import com.example.appcapook.R
import com.example.appcapook.model.api.Volume
import com.squareup.picasso.Picasso

class BookAdapter(
    private var items: List<Volume>
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    // ViewHolder interno para associar as views
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    // Método para atualizar os dados do adaptador
    fun updateBooks(newItems: List<Volume>) {
        items = newItems
        notifyDataSetChanged() // Atualiza a lista no RecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val volume = items[position]

        // Carregar a imagem com Picasso ou usar uma imagem padrão
        val imageUrl = volume.volumeInfo.imageLinks?.thumbnail
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.baseline_image_24)
        }

        // Configurar o clique no item para abrir a tela de detalhes do livro
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDatailsActivity::class.java)

            intent.putExtra("titulo", volume.volumeInfo.title)
            intent.putExtra("autor", volume.volumeInfo.authors?.joinToString(", ") ?: "Autor desconhecido")
            intent.putExtra("sinopse", volume.volumeInfo.description ?: "Sem descrição disponível")
            intent.putExtra("imageUrl", imageUrl)
            intent.putExtra("status", "Não lido") // Este valor pode ser atualizado no futuro

            context.startActivity(intent)
        }
    }
}
