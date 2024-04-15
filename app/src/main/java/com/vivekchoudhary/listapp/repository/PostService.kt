package com.vivekchoudhary.listapp.repository

import com.vivekchoudhary.listapp.repository.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int): Response<List<Post>>
}