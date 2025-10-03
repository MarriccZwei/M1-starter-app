package com.cpen321.usermanagement.data.remote.wl

import retrofit2.http.GET

interface MickiewiczInterface {
    @GET("authors/adam-mickiewicz/kinds/liryka/parent_books/")
    fun wierszeMickiewicza(): List<Book>
}