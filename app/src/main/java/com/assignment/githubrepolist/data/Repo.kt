package com.assignment.githubrepolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "repos")
data class Repo(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val ownerName: String?,
    val repoName: String?,
    val repoDescription: String?,
    val repoUrl: String?
)
