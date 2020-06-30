package com.example.shopping.view_holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shopping.R
import com.example.shopping.entity.ProductEntity
import com.example.shopping.model.ProductsItem
import com.example.shopping.view_model.CommonViewModal

class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    var image: ImageView? = null
    var name: TextView? = null
    var price: TextView? = null
    var quantity: TextView? = null
    var plus: ImageView? = null
    var minus: ImageView? = null

    init {

        image = itemview.findViewById(R.id.imageView)
        plus = itemview.findViewById(R.id.plus)
        minus = itemview.findViewById(R.id.minus)

        name = itemview.findViewById(R.id.name)
        price = itemview.findViewById(R.id.price)
        quantity = itemview.findViewById(R.id.countTxt)

    }

    fun bind(commonEntity: ProductEntity, commonViewModel: CommonViewModal) {


        Glide.with(itemView.context)
            .load(commonEntity.image)
            .placeholder(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(image!!)

        name?.text = commonEntity.name

        price?.text = "${commonEntity.price}"

        quantity?.text = "${commonEntity.quantity}"

        plus!!.setOnClickListener {
            var count = quantity?.text.toString().toInt()
            count++
            quantity!!.text = count.toString()

            val id = commonViewModel.checkISProductAlredyExist(commonEntity.productId!!)
            if (id > 0) {
                commonViewModel.updateQuantityInfo(id!!, quantity!!.text.toString().toInt())
            } else {
                commonEntity.selected = true
                commonViewModel.insertData(commonEntity)
            }

        }
        minus?.setOnClickListener {
            var count = quantity!!.text.toString().toInt()
            --count
            if (count == 0) {
                quantity!!.text = "0"
                val id = commonViewModel.checkISProductAlredyExist(commonEntity.productId!!)
                if (id > 0){
                    commonEntity.selected = false
                    commonViewModel.updateQuantityInfo(id!!,quantity!!.text.toString().toInt())
                }else {
                    commonEntity.selected = false
                    commonViewModel.insertData(commonEntity)
                }
            } else {
                commonEntity.selected = true
                quantity!!.text = count.toString()
                val id = commonViewModel.checkISProductAlredyExist(commonEntity.productId!!)
                commonViewModel.updateQuantityInfo(id, quantity!!.text.toString().toInt())
            }
        }
    }

}