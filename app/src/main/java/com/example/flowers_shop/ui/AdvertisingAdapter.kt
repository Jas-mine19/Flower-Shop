package com.example.flowers_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers_shop.R
import com.example.flowers_shop.models.Advertising
import com.example.flowers_shop.databinding.AdvertisingItemBinding

class AdvertisingAdapter : ListAdapter<Advertising, AdvertisingAdapter.ButtonViewHolder>(
    AdvertisingDiffUtil()
) {

    var onItemClick: ((Advertising) -> Unit)? = null
    var advertisingList: List<Advertising> = listOf()


    private class AdvertisingDiffUtil : DiffUtil.ItemCallback<Advertising>() {
        override fun areItemsTheSame(
            oldItem: Advertising,
            newItem: Advertising
        ): Boolean {
            return oldItem.cardId == newItem.cardId
        }

        override fun areContentsTheSame(
            oldItem: Advertising,
            newItem: Advertising
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.advertising_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = AdvertisingItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.binding.percent.text = getItem(position).discount

        holder.binding.apply {
            if(getItem(position).cardimage != null) {
                Glide
                    .with(holder.itemView.context)
                    .load(getItem(position).cardimage)
                    .centerCrop()
                    .into(advertisingBackground)
            } else {
                holder.binding.advertisingBackground.setImageResource(R.drawable.background_advert)
            }
            percent.text = getItem(position).discount

        }
    }


}