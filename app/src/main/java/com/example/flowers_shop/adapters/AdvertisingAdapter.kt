package com.example.flowers_shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Advertising

class AdvertisingAdapter : ListAdapter<Advertising, AdvertisingAdapter.ButtonViewHolder>(
    AdvertisingDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null
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
    ): AdvertisingAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_rv_item, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: TextView = view.findViewById(R.id.percent)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).cardId)
            }
        }
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.button.text = getItem(position).discount.toString()

    }

    fun setAdvertisingListItems(categoryList: List<Advertising>) {
        this.advertisingList = categoryList;
        notifyDataSetChanged()
    }
}