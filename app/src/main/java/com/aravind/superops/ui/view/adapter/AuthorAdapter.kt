package com.aravind.superops.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.aravind.superops.R
import com.aravind.superops.data.model.Author
import com.aravind.superops.data.model.Message
import com.aravind.superops.databinding.AdapterAuthorItemBinding
import com.bumptech.glide.Glide

class AuthorAdapter(
    private var authorList : ArrayList<Message>,
    val clickListener: onAuthorClickListener
    )
    : RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {

   inner class ViewHolder(val binding: AdapterAuthorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data : Message) {

//            Glide.with(binding.root).
//            load("http://message-list.appspot.com${data.author.photoUrl}").
//            into(binding.authorImageview)

            binding.authorImageview.load("http://message-list.appspot.com${data.author.photoUrl}") {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                transformations(CircleCropTransformation())
            }

            binding.authorNameTextview.text = data.author.name
            binding.descriptionTextview.text = data.updated

            binding.deleteTextview.setOnClickListener {
                clickListener.onAuthorClicked(data.author)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdapterAuthorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(authorList[position])
    }

    override fun getItemCount(): Int = authorList.size

    fun setFilter(list : ArrayList<Message>){
        this.authorList = list
    }

    interface onAuthorClickListener{
        fun onAuthorClicked(author : Author)
    }
}