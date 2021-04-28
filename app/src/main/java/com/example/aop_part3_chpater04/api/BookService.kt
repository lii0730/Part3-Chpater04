package com.example.aop_part3_chpater04.api

import com.example.aop_part3_chpater04.model.BestSellerDto
import com.example.aop_part3_chpater04.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") key : String,
        @Query("query") keyword: String
    ) : Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey : String
    ) : Call<BestSellerDto>
}