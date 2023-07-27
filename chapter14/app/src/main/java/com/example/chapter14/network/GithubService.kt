package com.example.chapter14.network

import com.example.chapter14.model.Repo
import com.example.chapter14.model.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    // https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user
//    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/repos")
    fun listRepos(
//        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("page") page: Int
    ): Call<List<Repo>>

    // https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-users
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ): Call<UserDTO>
}