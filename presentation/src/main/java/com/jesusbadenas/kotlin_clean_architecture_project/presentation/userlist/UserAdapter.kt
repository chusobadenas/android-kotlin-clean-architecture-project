package com.jesusbadenas.kotlin_clean_architecture_project.presentation.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.R
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.databinding.ItemUserBinding
import com.jesusbadenas.kotlin_clean_architecture_project.presentation.userlist.UserAdapter.UserViewHolder

class UserAdapter(
    private val listener: UserListener? = null
) : ListAdapter<User, UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.apply {
            bind(user)
            itemView.setOnClickListener {
                listener?.onUserItemClicked(user)
            }
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                this.user = user
                executePendingBindings()
            }
        }
    }

    internal class UserDiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id &&
                    oldItem.email == newItem.email &&
                    oldItem.imageUrl == newItem.imageUrl &&
                    oldItem.name == newItem.name &&
                    oldItem.website == newItem.website

    }
}
