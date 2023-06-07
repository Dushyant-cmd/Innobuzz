package com.example.innobuzzapp.data.network

import com.example.innobuzzapp.data.localdb.entities.PostsEntity
import com.example.innobuzzapp.data.repositories.responses.PostResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HomeNetwork {
    //Below is Call<PostsResponse> type method to make api call using retrofit and get posts
    @GET("posts")
    suspend fun getPosts(): Response<ArrayList<PostsEntity>>

    //Below is companion object so can access or call its method directly from class name like static
    //keyword in java
    companion object {
        val url = "https://jsonplaceholder.typicode.com"
        //below method is must have invoke name if defined in interface with operator keyword
        //else it will show error inapplicable operator keyword on function
        operator fun invoke(): HomeNetwork {
            //below is httpLogginInterceptor to monitor request and response
            //Body to see whole body of api call
            //client is OkHttpClient object that set on Retrofit instance.
            val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
            return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HomeNetwork::class.java)
        }
    }
}