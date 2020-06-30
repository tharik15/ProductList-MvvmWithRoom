package com.example.shopping.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(

	@field:SerializedName("image")
	var image: String? = null,

	@field:SerializedName("quantity")
	var quantity: Int? = null,

	@field:SerializedName("price")
	var price: String? = null,

	@field:SerializedName("product_id")
	var productId: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	var selected:Boolean?=null
)
