package com.example.berrybox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.berrybox.R
import com.example.berrybox.databinding.FruitItemBinding

class FruitAdapter(val listener: Listener): RecyclerView.Adapter<FruitAdapter.FruitHolder>() {
    val itemFruitList = ArrayList<ItemFruit>()
    class FruitHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = FruitItemBinding.bind(item)
        fun bind(itemFruit: ItemFruit, listener: Listener) = with(binding){
            im.setImageResource(itemFruit.avatarUrl)
            tvTitle.text = itemFruit.nameN
            if (itemFruit.favorite == 0){
                imFav.setImageResource(R.drawable.favorite__1_)
            }
            else{
                imFav.setImageResource(R.drawable.favorite__3_)
            }
            itemView.setOnClickListener {
                listener.onClick(itemFruit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        return FruitHolder(view)
    }

    override fun getItemCount(): Int {
        return itemFruitList.size
    }

    override fun onBindViewHolder(holder: FruitHolder, position: Int) {
        holder.bind(itemFruitList[position], listener)
    }

    fun addFruit(itemFruit: ItemFruit){
        itemFruitList.add(itemFruit)
        notifyDataSetChanged()
    }
    fun clear() {
        val size: Int = itemFruitList.size
        itemFruitList.clear()
        notifyItemRangeRemoved(0, size)
    }

    interface Listener{
        fun onClick(itemFruit: ItemFruit)
    }
}