package com.example.innobuzzapp.data.localdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostsEntity(
    @PrimaryKey
    var id: Int? = null,
    @ColumnInfo(name = "userId")
    var userId: Int? = null,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "body")
    var body: String? = null
)