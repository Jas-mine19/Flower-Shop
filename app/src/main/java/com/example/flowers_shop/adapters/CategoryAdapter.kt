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
import com.example.flowers_shop.data.Flowers

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ButtonViewHolder>(
    CategoryDiffUtil()
) {

    var onItemClick: ((Int) -> Unit)? = null
    var categoryList: List<Category> = listOf()



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
        holder.button.text = getItem(position).categoryName

        if (getItem(position).isSelected) {
            holder.button.setBackgroundResource(R.drawable.category_rv_shape_selected)

        } else {
            holder.button.setBackgroundResource(R.drawable.category_rv_item_shape_unselected)
        }
    }

    fun setCategoryListItems(categoryList: List<Category>) {
        this.categoryList = categoryList;
        notifyDataSetChanged()
    }


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: TextView = view.findViewById(R.id.name)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition).categoryId)
            }
        }
    }
}