package com.example.chapter07.viewpager2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.chapter07.R
import com.example.chapter07.databinding.ItemSlideBinding
import com.example.chapter07.viewpager2.model.ItemData


/**
 * Use RecyclerView.Adapter<RecyclerView.ViewHolder>()
 *
 * data set : ItemData - packageName/viewpager2/model/ItemData
 */
class SlideRecyclerViewAdapter(
    private val itemList: List<ItemData>
): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /** Use view inflate */
//        val inflater =parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_slide, parent, false)
        /** Use viewBinding */
        val binding = ItemSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}

class ViewHolder(private val binding: ItemSlideBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(item: ItemData){
        binding.textViewId.text = item.id.toString()
        binding.textViewDesc.text = item.desc
    }
}

