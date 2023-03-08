package com.assignment.githubrepolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse

@Database(
    entities = [RepoDetailResponse::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun getRepoDAO(): RepoDAO
}

