package com.example.shopping.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ProductTable",
    indices = arrayOf(Index(value = ["ProductTitle"], unique = true))
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,


    var productId: String? = null,
    @ColumnInfo(name = "Image")
    var image: String? = null,

    @ColumnInfo(name = "Qty")
    var quantity: Int? = null,

    @ColumnInfo(name = "Price")
    var price: String? = null,

    @ColumnInfo(name = "ProductTitle")
    var name: String? = null,

    var selected: Boolean? = null

)