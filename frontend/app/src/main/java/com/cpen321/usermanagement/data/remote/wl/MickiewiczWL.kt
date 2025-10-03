package com.cpen321.usermanagement.data.remote.wl

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object MickiewiczWL {
    private const val BASE_URL = "https://wolnelektury.pl/api/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val mickiewiczInterface: MickiewiczInterface = retrofit.create(MickiewiczInterface::class.java)

    var selectedPoem = MickiewiczPoem()
    private var poemList = listOf<MickiewiczPoem>(selectedPoem)

    suspend fun loadAll(){
        try{
            val response = mickiewiczInterface.getAllPoems()
            if (response.isSuccessful){
                poemList = response.body()?:poemList
            }
            else{
                poemList = listOf<MickiewiczPoem>(MickiewiczPoem())
            }
        } catch (e: Exception){
            Error(e.message) //here to see the error message with debuger
        }
    }
}