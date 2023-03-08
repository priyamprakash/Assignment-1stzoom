package com.assignment.githubrepolist.repository

import com.assignment.githubrepolist.api.GitApi
import javax.inject.Inject

class GitRepository @Inject constructor(private val gitApi: GitApi) {

    suspend fun getRepositoryDetail(username: String, reponame: String) =
        gitApi.getUserRepos(username, reponame)

}