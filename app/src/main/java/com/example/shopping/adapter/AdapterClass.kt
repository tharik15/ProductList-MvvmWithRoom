package com.example.shopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.entity.ProductEntity
import com.example.shopping.model.ProductsItem
import com.example.shopping.view_holder.ViewHolder
import com.example.shopping.view_model.CommonViewModal

class AdapterClass(var context: Context?, var list:ArrayList<ProductEntity>,
                   var commonViewModel: CommonViewModal):RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.poduct_list,parent,false))
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var obj = list[position]

        holder.bind(obj,commonViewModel)
    }

}