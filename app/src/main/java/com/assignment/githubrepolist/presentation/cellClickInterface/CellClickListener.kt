package com.assignment.githubrepolist.presentation.cellClickInterface

import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail


interface CellClickListener {
    fun onCellClickListener(operation: String, item: RepoDetail)
}