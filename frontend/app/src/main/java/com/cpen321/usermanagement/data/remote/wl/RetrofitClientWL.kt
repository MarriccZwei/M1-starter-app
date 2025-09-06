package com.cpen321.usermanagement.data.remote.wl

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientWL {
    private const val BASE_URL = "https://wolnelektury.pl/api/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mickiewiczInterface: MickiewiczInterface = retrofit.create(MickiewiczInterface::class.java)

    val poems = this.mickiewiczInterface.wierszeMickiewicza() //we get the data only once to limit the lag at runtime

    public fun RandomPoem(): Book{
        return poems.random()
    }
}