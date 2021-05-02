package com.example.aop_part3_chpater04.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aop_part3_chpater04.model.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review Where id == :id")
    fun getOneReview(id : Int) : Review

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review: Review)
}