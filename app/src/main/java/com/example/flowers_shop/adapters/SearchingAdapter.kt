package com.example.flowers_shop.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowers_shop.R
import com.example.flowers_shop.data.Flowers
import com.example.flowers_shop.databinding.SearchingRvBinding
import java.util.*

class SearchingAdapter :
    ListAdapter<Flowers, SearchingAdapter.ButtonViewHolder>(SearchingAdapter.FlowerDiffUtil()) {
    var onItemClick: ((Flowers) -> Unit)? = null
    private var list = listOf<Flowers>()

//    private val flowersList = ArrayList<Flowers>()
//    var flowersFilterList = ArrayList<Flowers>()
//
//    init {
//        flowersFilterList = flowersList
//    }

    private class FlowerDiffUtil : DiffUtil.ItemCallback<Flowers>() {
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
            return oldItem.isFavorite == newItem.isFavorite
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchingAdapter.ButtonViewHolder =
        ButtonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.searching_rv, parent, false)
        )


    inner class ButtonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = SearchingRvBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }


    override fun onBindViewHolder(holder: SearchingAdapter.ButtonViewHolder, position: Int) {
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
            flowerName.text = getItem(position).flowerName
        }

    }


    fun setData(list: List<Flowers>) {
        this.list = list
        submitList(list)
    }

    fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            Log.d("filter1", "performFiltering: ")
            val filteredList = mutableListOf<Flowers>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.flowerName.lowercase(Locale.ROOT)
                            .startsWith(constraint.toString().lowercase(Locale.ROOT))
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            Log.d("filter2", "publishResults: ")
            submitList(filterResults?.values as List<Flowers>)
        }


//    fun getFilter(): Filter {
//        Log.d("filter1", "getFilter: ")
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//
//                Log.d("filter2", "performFiltering: ")
//
//                val searchText = constraint.toString().lowercase()
//                flowersFilterList = if (searchText.isEmpty()) {
//                    flowersList
//                } else {
//                    val resultList = ArrayList<Flowers>()
//                    for (row in flowersList) {
//                        row.flowerName?.let { flowerName ->
//                            if (flowerName.lowercase().contains(searchText)
//                                   ) {
//                                resultList.add(row)
//                            }
//                        }
//                    }
//                    resultList
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = flowersFilterList
//                Log.d("filter3", "flowerlist: ")
//
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                flowersFilterList = results?.values as ArrayList<Flowers>
//                notifyItemRangeChanged(0, flowersFilterList.size)
//            }
//        }
//    }
    }
}


