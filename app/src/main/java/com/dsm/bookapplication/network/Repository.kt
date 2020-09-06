package com.dsm.bookapplication.network

import com.dsm.bookapplication.model.Book
import com.dsm.bookapplication.model.Ticker
import com.dsm.bookapplication.network.deserializers.GetBooksDeserializer
import com.dsm.bookapplication.network.deserializers.GetTickerDeserializer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private var apis: Apis

    init {

        var gsonBuilder = GsonBuilder().also {
            it.registerTypeHierarchyAdapter(List::class.java,
                GetBooksDeserializer()
            )
            it.registerTypeHierarchyAdapter(Ticker::class.java,
                GetTickerDeserializer()
            )
        }


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.bitso.com")
            .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

        apis = retrofit.create(Apis::class.java)
    }



    fun getBooks(callback: Callback<List<Book>>) = apis.requestBooks().enqueue(callback)


    suspend fun getTicker(bookName: String): Response<Ticker> = apis.requestTicker(bookName)



}