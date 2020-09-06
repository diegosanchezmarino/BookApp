package com.dsm.bookapplication.network

import com.dsm.bookapplication.model.Book
import com.dsm.bookapplication.model.Ticker
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface Apis {

    @GET("/v3/available_books")
    fun requestBooks(): Call<List<Book>>

    @GET("/v3/ticker")
    suspend fun requestTicker(@Query("book") bookName: String): Response<Ticker>


}