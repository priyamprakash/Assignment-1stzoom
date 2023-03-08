package com.assignment.githubrepolist.api

import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {

    @GET("repos/{username}/{reponame}")
    suspend fun getUserRepos(@Path("username") username : String, @Path("reponame") reponame: String) : Response<RepoDetailResponse>

}