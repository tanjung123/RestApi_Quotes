package com.example.kuismws

import retrofit2.Call
import retrofit2.http.GET

interface QuotesAPI {
    @GET("api/quotes")
    fun getQuotes() : Call<List<Quotes>>
}