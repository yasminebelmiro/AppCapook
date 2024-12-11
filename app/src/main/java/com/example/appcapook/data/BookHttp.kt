package com.example.appcapook.data

import com.example.appcapook.model.api.SearchResult
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object BookHttp {
    private const val API_KEY = "AIzaSyA1-IqjfkIztE_AZp2NAdqHv0xbjrN5UH8"
    private const val BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&key=$API_KEY&maxResults=40"
    private val gson = Gson()

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchBook(q: String): SearchResult? {
        val request = Request.Builder()
            .url(String.format(BASE_URL,q))
            .build()

        return try {
            val response = client.newCall(request).execute()

            // Verificar se a resposta foi bem-sucedida
            if (response.isSuccessful) {
                val body = response.body() // Acessar o corpo da resposta com .body()
                val json = body?.string()  // Ler o corpo como string

                // Verificar se o corpo não é null e deserializar
                if (json != null) {
                    gson.fromJson(json, SearchResult::class.java)
                } else {
                    println("Erro: Corpo da resposta está vazio")
                    null
                }
            } else {
                println("Erro na requisição: Código ${response.code()}")
                null
            }
        } catch (e: Exception) {
            println("Erro ao buscar livros: ${e.message}")
            null
        }
    }
}