package com.example.shopping.interfaces

import com.example.shopping.model.ProductResponse
import retrofit2.http.GET

interface DataServiceInterface {

    @GET("v2/5def7b172f000063008e0aa2")
    fun getProduct():retrofit2.Call<ProductResponse>

}