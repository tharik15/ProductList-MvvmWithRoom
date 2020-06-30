package com.example.shopping.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopping.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductTable  ORDER BY id ASC")
    fun getAllProducts(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM ProductTable WHERE selected = 1 ORDER BY id ASC")
    fun getCartList(): LiveData<List<ProductEntity>>

    @Query("UPDATE ProductTable SET Qty = :saleQuantity WHERE id = :Ids")
    fun updateQuantityInfo(Ids: Int, saleQuantity: Int)

    @Query("SELECT id FROM ProductTable WHERE productId =:productIds LIMIT 1")
    fun checkAlredyExist(productIds: String):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commonEntity: ProductEntity):Long
}