package com.example.flowers_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers_shop.R
import com.example.flowers_shop.models.Flowers
import com.example.flowers_shop.databinding.FavoriteRvItemBinding

class FavoriteAdapter() : ListAdapter<Flowers, FavoriteAdapter.ButtonViewHolder>(
    FavoriteDiffUtil()
) {

    var onItemClick: ((Flowers) -> Unit)? = null


    private class FavoriteDiffUtil : DiffUtil.ItemCallback<Flowers>() {
        override fun areItemsTheSame(
            oldItem: Flowers,
            newItem: Flowers
        ): Boolean {
            return oldItem.flowerId == newItem.flowerId
        }

        override fun areContentsTheSame(
            oldItem: Flowers,
            newItem: Flowers
        ): Boolean {
            return oldItem.flowerId == newItem.flowerId
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_rv_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = FavoriteRvItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.binding.apply {
            if (getItem(position).imageURL != null) {
                Glide
                    .with(holder.itemView.context)
                    .load(getItem(position).imageURL)
                    .centerCrop()
                    .into(image)
            } else {
                holder.binding.image.setImageResource(R.drawable.background_advert)
            }
            name.text = getItem(position).flowerName
            price.text = getItem(position).flowerPrice

        }

    }
}