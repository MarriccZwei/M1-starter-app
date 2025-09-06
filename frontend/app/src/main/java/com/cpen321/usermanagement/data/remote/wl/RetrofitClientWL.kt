package com.cpen321.usermanagement.data.remote.wl

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientWL {
    private const val BASE_URL = "https://wolnelektury.pl/api/"

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val mickiewiczInterface: MickiewiczInterface = retrofit.create(MickiewiczInterface::class.java)

    private var poems:List<Book> = emptyList()

    fun loadPoems(){
        try{poems =  mickiewiczInterface.wierszeMickiewicza()}
        catch (e: Exception){
            poems = listOf(Book(title = e.message.toString(), author = e.message.toString(), url=e.message.toString()))
        }
    }
    var selectedPoem:Book = Book(title = "WrongBook", author = "WronAuth", url="wrong_url")

    fun updatePoem(){
        selectedPoem = poems.random()
    }
}