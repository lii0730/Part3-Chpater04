package com.example.aop_part3_chpater04

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aop_part3_chpater04.Dao.HistoryDao
import com.example.aop_part3_chpater04.Dao.ReviewDao
import com.example.aop_part3_chpater04.model.History
import com.example.aop_part3_chpater04.model.Review

@Database(entities = [History::class, Review::class], version = 2)
abstract class appDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun reViewDao(): ReviewDao
}

fun getAppDatabase(context: Context): appDatabase {

    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE 'REVIEW'('id' INTEGER, 'review' TEXT," + "PRIMARY KEY('id'))"
            )
        }
    }

    return Room.databaseBuilder(
        context,
        appDatabase::class.java,
        "BookSearchDB"
    )
        .addMigrations(migration_1_2)
        .build()
}
