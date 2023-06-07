package com.example.innobuzzapp.data.localdb.daos

import androidx.room.*
import com.example.innobuzzapp.data.localdb.entities.PostsEntity

@Dao
interface PostsDao {
    @Insert
    fun insertPosts(posts: PostsEntity)

    @Query("select * from posts")
    fun queryPosts(): List<PostsEntity>

    @Delete
    fun deletePosts(posts: PostsEntity)

    @Query("delete from posts")
    fun deleteAllPosts()

    @Update
    fun updatePosts(posts: PostsEntity)
}