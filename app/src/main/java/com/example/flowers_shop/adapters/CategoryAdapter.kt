package com.example.flowers_shop.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ButtonViewHolder>(
    CategoryDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null


    private class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_rv_item, parent, false)
        )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CategoryAdapter.ButtonViewHolder, position: Int) {
        holder.button.text = getItem(position).name

        if (getItem(position).isSelected) {
            holder.button.setBackgroundResource(R.drawable.category_rv_shape_selected)

        } else {
            holder.button.setBackgroundResource(R.drawable.category_rv_item_shape_unselected)
        }
    }



    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: TextView = view.findViewById(R.id.name)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).id)
            }
        }
    }
}