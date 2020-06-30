package com.example.shopping

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping.adapter.AdapterClass
import com.example.shopping.entity.ProductEntity
import com.example.shopping.view_model.CommonViewModal
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*

class CartActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    private var commonViewModal: CommonViewModal? = null
    var list = ArrayList<ProductEntity>()
    lateinit var adapter : AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        commonViewModal = ViewModelProviders.of(this).get(CommonViewModal::class.java)

        cart_recycler.layoutManager = LinearLayoutManager(this)
        cart_recycler.setHasFixedSize(true)

        adapter = AdapterClass(this,list!!,commonViewModal!!)

        commonViewModal!!.allFinalProducts.observe(this, Observer { res ->
            run {

                if (res.isNotEmpty()) {

                    list = res as ArrayList<ProductEntity>
                    supportActionBar!!.title = "My Cart (${list.size})"
                    adapter = AdapterClass(applicationContext,list,commonViewModal!!)
                    cart_recycler.adapter = adapter
//                    adapter.notifyDataSetChanged()

                    no_item_msg.visibility = View.GONE
                }else{
                    list = res as ArrayList<ProductEntity>
                    supportActionBar!!.title = "My Cart (${list.size})"
                    adapter = AdapterClass(applicationContext,list,commonViewModal!!)
                    cart_recycler.adapter = adapter
                    no_item_msg.visibility = View.VISIBLE
                }

            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        onBackPressed()
    }

    override fun onBackPressed() {
        return super.onBackPressed()
    }
}