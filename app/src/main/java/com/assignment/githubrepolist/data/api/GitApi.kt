package com.assignment.githubrepolist.data.api

import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {

    @GET("repos/{username}/{reponame}")
    suspend fun getUserRepos(@Path("username") username : String, @Path("reponame") reponame: String) : Response<RepoDetail>

}