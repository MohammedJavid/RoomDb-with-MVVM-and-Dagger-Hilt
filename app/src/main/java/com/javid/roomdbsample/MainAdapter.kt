package com.javid.roomdbsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javid.roomdbsample.databinding.LayoutListItemBinding
import com.javid.roomdbsample.room.MainEntity

class MainAdapter(private val list: List<MainEntity>,private val clickListener:(MainEntity)-> Unit)
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: LayoutListItemBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(mainEntity: MainEntity, clickListener: (MainEntity) -> Unit) {
            binding.apply {
                tvIdTxt.text = mainEntity.empId.toString()
                tvNameTxt.text = mainEntity.empName
                tvPositionTxt.text = mainEntity.empPosition
            }

            binding.clView.setOnClickListener {
                clickListener(mainEntity)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = LayoutListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position],clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}