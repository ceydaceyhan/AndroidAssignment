package com.ceydaceyhan.androidassignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ceydaceyhan.androidassignment.databinding.RecyclerViewItemBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.KisilerVH>() {
    private var myData: ArrayList<Data> = arrayListOf()

    class KisilerVH(itemView: RecyclerViewItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var sentBinding: RecyclerViewItemBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KisilerVH {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewItemBinding>(
            inflater,
            R.layout.recycler_view_item, parent, false
        )
        return KisilerVH(view)
    }

    override fun onBindViewHolder(holder: KisilerVH, position: Int) {
        holder.sentBinding.idText.text = myData.get(position).id.toString()
        holder.sentBinding.firstNameText.text = myData.get(position).firstName
        holder.sentBinding.lastNameText.text = myData.get(position).lastName
        Picasso.get().load(myData.get(position).avatar).into(holder.sentBinding.imageView)

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("selectedItem", myData.get(position).id.toString())
            }
            Navigation.findNavController(it).navigate(R.id.detayEkraninaGecis, bundle)
        }

    }

    override fun getItemCount(): Int {
        return myData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<Data>) {
        myData.clear()
        myData.addAll(data)
        notifyDataSetChanged()

    }
}