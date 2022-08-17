package com.example.flowers_shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Favorite

class FavoriteAdapter : ListAdapter<Favorite, FavoriteAdapter.ButtonViewHolder>(
    FavoriteDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null


    private class FavoriteDiffUtil : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(
            oldItem: Favorite,
            newItem: Favorite
        ): Boolean {
            return oldItem.favoriteId == newItem.favoriteId
        }

        override fun areContentsTheSame(
            oldItem: Favorite,
            newItem: Favorite
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_rv_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: ImageButton = view.findViewById(R.id.basket_button)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).favoriteId)
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {


    }
}