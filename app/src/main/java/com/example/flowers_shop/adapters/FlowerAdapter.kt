package com.example.flowers_shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Flowers

class FlowerAdapter : ListAdapter<Flowers, FlowerAdapter.ButtonViewHolder>(
    FlowerDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null


    private class FlowerDiffUtil : DiffUtil.ItemCallback<Flowers>() {
        override fun areItemsTheSame(
            oldItem: Flowers,
            newItem: Flowers
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Flowers,
            newItem: Flowers
        ): Boolean {
            return oldItem.isFavorite == newItem.isFavorite
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlowerAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_rv_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: ImageView = view.findViewById(R.id.favorite_button)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).id)
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
            if (getItem(position).isFavorite) {
            holder.button.setImageResource(R.drawable.icon_favorite_selected)

        } else {
            holder.button.setImageResource(R.drawable.icon_favorite_unselected)
        }



    }
}