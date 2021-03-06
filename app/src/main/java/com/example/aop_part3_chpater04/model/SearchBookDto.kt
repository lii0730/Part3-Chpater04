package com.example.aop_part3_chpater04.model

import com.google.gson.annotations.SerializedName

data class SearchBookDto (
    @SerializedName("title") val title : String,
    @SerializedName("item") val books: List<Book>
)