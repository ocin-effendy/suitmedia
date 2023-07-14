package com.code.suitmedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.suitmedia.data.resource.remote.response.DataItem
import com.code.suitmedia.databinding.ItemUserBinding

    class UserListAdapter(private val onClickListener: OnClickListener):
        PagingDataAdapter<DataItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {

        private var selectedUserName: String? = null
        private var selectedUserId: Int? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val data = getItem(position)
            if (data != null) {
                holder.bind(data)
                holder.itemView.setOnClickListener {
                    onClickListener.onItemClick(data)
                    selectedUserName = data.firstName + " " + data.lastName
                    selectedUserId = data.id
                    notifyDataSetChanged()
                }
            }
        }

        class MyViewHolder(private val binding: ItemUserBinding) :
            RecyclerView.ViewHolder(binding.root) {
            @SuppressLint("SetTextI18n")
            fun bind(data: DataItem) {
                Glide.with(binding.root)
                    .load(data.avatar)
                    .into(binding.photoUser)

                binding.emailUser.text = data.email
                binding.nameUser.text = data.firstName+ " " + data.lastName

                binding.root.setOnClickListener {

                }
            }
        }

        interface OnClickListener {
            fun onItemClick(data: DataItem)
        }

        companion object {
            val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
                override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                    return oldItem.id == newItem.id
                }
            }
        }
    }