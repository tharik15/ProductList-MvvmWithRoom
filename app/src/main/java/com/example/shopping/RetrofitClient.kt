package com.example.shopping

import com.example.shopping.interfaces.DataServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://www.mocky.io/"

    fun getService() : DataServiceInterface {

        if (retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!.create(DataServiceInterface::class.java)
    }



}