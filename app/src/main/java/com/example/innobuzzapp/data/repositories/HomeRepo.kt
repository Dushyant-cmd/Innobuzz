package com.example.innobuzzapp.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.innobuzzapp.data.localdb.DatabaseHelper
import com.example.innobuzzapp.data.localdb.entities.PostsEntity
import com.example.innobuzzapp.data.network.HomeNetwork
import com.example.innobuzzapp.data.repositories.responses.PostResponse
import com.example.innobuzzapp.utils.DisplayUtilities

class HomeRepo {
    val TAG: String = "HomeRepository.kt"
    var dbHelper: DatabaseHelper? = null
    //It is repository for MainActivity View View-Model to crud with content provider like from
    //External storage, LocalSQLite(room db), web-services
    suspend fun getPosts(context: Context): LiveData<ArrayList<PostsEntity>> {
        dbHelper = DatabaseHelper.getRoomDb(context)
        val apiResponse = MutableLiveData<ArrayList<PostsEntity>>()
        val response = HomeNetwork().getPosts()
        if(response.isSuccessful) {
            Log.d(TAG, "getPosts: api data ${response.body()?.size}")
            //insert all response body list item objects into SQLite database using room
            val dao = dbHelper?.getPostsDao()
            dao?.deleteAllPosts()

            response.body()?.forEach {
                dao?.insertPosts(it)
            }

            //map all the post entity object
            apiResponse.value = dao?.queryPosts() as ArrayList
            Log.d(TAG, "getPosts: room data ${dao.queryPosts().size}")
        }
        return apiResponse
    }
}