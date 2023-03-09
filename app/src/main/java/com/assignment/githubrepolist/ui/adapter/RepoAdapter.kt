package com.assignment.githubrepolist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.githubrepolist.R
import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse

class RepoAdapter(private val context: Context): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    var items: ArrayList<RepoDetailResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo_card, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = items[position]
        holder.textRepositoryName.text = item.name
        holder.repoDescription.text = item.description


    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textRepositoryName: TextView = itemView.findViewById(R.id.text_repository_name)
        val repoDescription: TextView = itemView.findViewById(R.id.text_description)
        val shareButton: Button = itemView.findViewById(R.id.shareButton)
        val repoCardView: CardView = itemView.findViewById(R.id.repoCardView)
    }

    fun setData(data: List<RepoDetailResponse>) {
        this.items = data as ArrayList<RepoDetailResponse>
        notifyDataSetChanged()
    }

}