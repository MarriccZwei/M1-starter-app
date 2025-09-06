package com.cpen321.usermanagement.data.remote.wl

import retrofit2.Response
import retrofit2.http.GET

interface MickiewiczInterface {
    @GET("authors/adam-mickiewicz/kinds/liryka/parent_books/")
    suspend fun getAllPoems(): Response<List<MickiewiczPoem>>
}