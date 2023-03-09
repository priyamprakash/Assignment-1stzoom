package com.assignment.githubrepolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail

@Database(
    entities = [RepoDetail::class],
    version = 1,
    exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun getRepoDAO(): RepoDAO
}

