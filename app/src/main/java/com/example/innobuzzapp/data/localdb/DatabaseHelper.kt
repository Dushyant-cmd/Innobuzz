package com.example.innobuzzapp.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.innobuzzapp.data.localdb.daos.PostsDao
import com.example.innobuzzapp.data.localdb.entities.PostsEntity

@Database(entities = [PostsEntity::class], exportSchema = false, version = 1)
abstract class DatabaseHelper: RoomDatabase() {
    //below is dao interface return abstract method
    abstract fun getPostsDao(): PostsDao

    companion object {
        var DB_NAME = "db"
        var instance: DatabaseHelper? = null

        fun getRoomDb(context: Context): DatabaseHelper {
            if(instance == null) {
                return Room.databaseBuilder(context, DatabaseHelper::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}