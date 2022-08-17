package com.example.flowers_shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Basket

class BasketAdapter : ListAdapter<Basket, BasketAdapter.ButtonViewHolder>(
    BasketDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null


    private class BasketDiffUtil : DiffUtil.ItemCallback<Basket>() {
        override fun areItemsTheSame(
            oldItem: Basket,
            newItem: Basket
        ): Boolean {
            return oldItem.basketId == newItem.basketId
        }

        override fun areContentsTheSame(
            oldItem: Basket,
            newItem: Basket
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_rv_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: ImageButton = view.findViewById(R.id.basket_button)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).basketId)
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {


    }
}