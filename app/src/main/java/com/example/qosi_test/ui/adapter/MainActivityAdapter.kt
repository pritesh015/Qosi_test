package com.example.qosi_test.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.qosi_test.R
import com.example.qosi_test.databinding.ItemUsersBinding
import com.example.qosi_test.models.ResponseUser

class MainActivityAdapter(val context: Context, var items: ArrayList<ResponseUser>): RecyclerView.Adapter<MainActivityAdapter.UserDetailHolder>() {
    var listener: MainActivityAdapter.OnBottomReachedListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailHolder {
        return UserDetailHolder(
            LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserDetailHolder, position: Int) {
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

        if (items[position].picture?.mediumPicture == null) {
            Glide.with(context)
                .load(R.drawable.placeholder)
                .apply(requestOptions)
                .into(holder.binding.ivUser)
        } else {
            Glide.with(context)
                .load(items[position].picture?.mediumPicture)
                .apply(requestOptions)
                .into(holder.binding.ivUser)
        }
        var name = items[position].name?.last
        name = name.plus(" ")
        name = name.plus(items[position].name?.first)

        holder.binding.tvName.text = name

        holder.binding.ivTrashBtn.setOnClickListener {
            val currPosition = holder.adapterPosition
            items.removeAt(currPosition)
            notifyItemRemoved(currPosition)
        }

        if (position == items.size - 1) {
            listener?.onBottomReached(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(list: List<ResponseUser>) {
        items = list as ArrayList<ResponseUser>
        notifyDataSetChanged()
    }

    interface OnBottomReachedListener {
        fun onBottomReached(position: Int)
    }

    class UserDetailHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsersBinding.bind(view)
    }
}