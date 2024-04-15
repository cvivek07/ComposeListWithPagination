package com.vivekchoudhary.listapp.repository

import com.vivekchoudhary.listapp.common.DispatcherProvider
import com.vivekchoudhary.listapp.repository.model.Post
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PostsRepository {
    suspend fun getPosts(page: Int): Result<List<Post>>
}

class PostsRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val apiService: PostService) :
    PostsRepository {


    override suspend fun getPosts(page: Int): Result<List<Post>> {
        return withContext(dispatcherProvider.io()) {
            val response = apiService.getPosts(page)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@withContext Result.success(it)
                }
            }
            return@withContext Result.failure(ApiException("Something went wrong"))
        }
    }

}

class ApiException(message: String): Exception(message)