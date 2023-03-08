package com.assignment.githubrepolist.model.repodetail.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "repos")
data class RepoDetailResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val description: String?= null,
    val html_url: String,
    val name: String
)