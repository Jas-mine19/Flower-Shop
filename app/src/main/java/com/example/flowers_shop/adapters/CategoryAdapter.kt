package com.example.flowers_shop.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Category
import com.example.flowers_shop.data.Flowers
import com.example.flowers_shop.databinding.CategoryRvItemBinding

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ButtonViewHolder>(
    CategoryDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null

    private class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.categoryId == newItem.categoryId
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_rv_item, parent, false)
        )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CategoryAdapter.ButtonViewHolder, position: Int) {
        holder.binding.apply {
            categoryName.text = getItem(position).categoryName
            if(getItem(position).imageUrl != null) {
                Glide
                    .with(holder.itemView.context)
                    .load(getItem(position).imageUrl)
                    .centerCrop()
                    .into(image)
            }
        }
        if (getItem(position).isSelected) {
            holder.binding.image.setBackgroundResource(R.drawable.category_rv_shape_selected)
        } else {
            holder.binding.image.setBackgroundResource(R.drawable.category_rv_item_shape_unselected)
        }
    }

    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryRvItemBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).categoryId)
            }
        }
    }
}