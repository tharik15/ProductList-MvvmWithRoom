package com.example.shopping.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shopping.entity.ProductEntity
import com.example.shopping.model.ProductsItem
import com.example.shopping.repository.ProductRepository
import com.example.shopping.room.ItemDatabase

class CommonViewModal(application: Application) : AndroidViewModel(application) {

    var db:ItemDatabase = ItemDatabase.getInstance(application)
    val repository = ProductRepository()

    // get database data's

    internal val allProducts: LiveData<List<ProductEntity>> = db.productDao().getAllProducts()
    internal val allFinalProducts: LiveData<List<ProductEntity>> = db.productDao().getCartList()

    fun insertData(commonEntity: ProductEntity): Long {
        return db.productDao().insert(commonEntity)
    }

    fun updateQuantityInfo(Id: Int, saleQuantity: Int) {
        return db.productDao().updateQuantityInfo(Id, saleQuantity)

    }

    fun checkISProductAlredyExist(productID:String):Int{

        return db.productDao().checkAlredyExist(productID)
    }



    // get Product List from api
    fun getProductList() :LiveData<List<ProductEntity>>{

        return repository.getMutableLiveData()
    }


}