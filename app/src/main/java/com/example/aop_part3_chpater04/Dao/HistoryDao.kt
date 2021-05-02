package com.example.aop_part3_chpater04.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aop_part3_chpater04.model.History

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History")
    fun getAll() : List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history WHERE keyword == :keyword")
    fun delete(keyword : String)
}


