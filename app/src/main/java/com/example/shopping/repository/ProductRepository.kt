package com.example.shopping.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shopping.RetrofitClient
import com.example.shopping.entity.ProductEntity
import com.example.shopping.model.ProductResponse
import com.example.shopping.model.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class ProductRepository {


    var productList = ArrayList<ProductsItem>()
    var tempList = ArrayList<ProductEntity>()

    private var mutableLiveData = MutableLiveData<List<ProductEntity>>()

    val client = RetrofitClient()

    fun getMutableLiveData(): MutableLiveData<List<ProductEntity>> {

        val dataService = client.getService()
        val call: Call<ProductResponse> = dataService.getProduct()
        call.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

                try {
                    Log.i("Repository", t.message!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onResponse(call: Call<ProductResponse>, response: retrofit2.Response<ProductResponse>
            ) {
                try {
                    val response = response.body()
                    tempList.clear()
                    if (response?.products != null) {
                        productList = response.products as ArrayList<ProductsItem>

                        for (i in 0 until productList.size){
                            var obj = ProductEntity()
                            obj.name = productList[i].name
                            obj.price = productList[i].price
                            obj.image = productList[i].image
                            obj.quantity = 0
                            tempList.add(obj)
                        }

                    mutableLiveData.value = tempList
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        })
        return mutableLiveData
    }


}