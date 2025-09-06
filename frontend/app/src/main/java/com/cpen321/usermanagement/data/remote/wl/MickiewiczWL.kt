package com.cpen321.usermanagement.data.remote.wl

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import android.util.Log


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
    private var poemList = listOf<MickiewiczPoem>(MickiewiczPoem())

    suspend fun loadAll(){
        try{
            val response = mickiewiczInterface.getAllPoems()
            if (response.isSuccessful){
                poemList = response.body()?:poemList
            }
            else{
                poemList = listOf<MickiewiczPoem>(MickiewiczPoem(
                    title = "Error! Could not get data from Wolne Lektury API."
                )) //so that the app can continue even without the poems, it ain't very critical
                Log.e("WL", "Could not get the data from Wolne Lektury API")
            }
        } catch (e: Exception){
            poemList = listOf<MickiewiczPoem>(MickiewiczPoem(
                title = "Error! Failed to connect to Wolne Lektury API."
            )) //so that the app can continue even without the poems, it ain't very critical
            Log.e("WL", e.message.toString())
        }
    }

    fun updateSelectedPoem(){
        selectedPoem = poemList.random()
    }
}