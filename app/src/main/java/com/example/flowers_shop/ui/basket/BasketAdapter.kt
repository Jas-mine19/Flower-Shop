package com.example.flowers_shop.ui.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers_shop.R
import com.example.flowers_shop.models.Flowers
import com.example.flowers_shop.databinding.BasketRvItemBinding

class BasketAdapter : ListAdapter<Flowers, BasketAdapter.ButtonViewHolder>(
    BasketDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null


    private class BasketDiffUtil : DiffUtil.ItemCallback<Flowers>() {
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
                .inflate(R.layout.basket_rv_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = BasketRvItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).flowerId)
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.binding.apply {
            Glide
                .with(holder.itemView.context)
                .load(getItem(position).imageURL)
                .centerCrop()
                .into(image)

            name.text = getItem(position).flowerName
            priceText.text = getItem(position).flowerPrice
            numOfFlower.text = getItem(position).numberOfFlower.toString()
            tvPrice.text = getItem(position).totalPrice.toString()
            var flowerprice = ""
            flowerprice = getItem(position).flowerPrice
            var number =getItem(position).numberOfFlower
            btnAdd.setOnClickListener {
                if (number >= 0) {
                    number += 1
                    numOfFlower.text = number.toString()
                    val price = flowerprice.toInt()
                    val num = number * price
                    tvPrice.text = num.toString()
                }

            }

            btnDelete.setOnClickListener {
                if (number >= 1) {
                    number -= 1
                    numOfFlower.text = number.toString()
                    val price = flowerprice.toInt()
                    val num = (price * (number + 1)) - price
                    tvPrice.text = num.toString()
                } else {
                    number = 0
                }
            }


        }

    }
}