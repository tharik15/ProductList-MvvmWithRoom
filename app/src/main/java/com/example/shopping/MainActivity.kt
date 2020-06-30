package com.example.shopping

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.adapter.AdapterClass
import com.example.shopping.entity.ProductEntity
import com.example.shopping.model.ProductsItem
import com.example.shopping.view_model.CommonViewModal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val TAG = MainActivity::class.java.simpleName

    private var commonViewModal: CommonViewModal? = null
    var list = ArrayList<ProductEntity>()
    var noNetwork: View? = null
    lateinit var adapter : AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        commonViewModal = ViewModelProviders.of(this).get(CommonViewModal::class.java)
        noNetwork = findViewById(R.id.networkLayout)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        adapter = AdapterClass(this,list!!,commonViewModal!!)

        if (isNetworkAvailable()) {
            getProductResponse()
        } else {
            recycler.visibility = View.GONE
            progress.visibility = View.GONE
            noNetwork?.visibility = View.VISIBLE
        }

        commonViewModal!!.allFinalProducts.observe(this, Observer { list ->
            run {

                if (list.isNotEmpty()) {

                    addToCart.visibility =View.VISIBLE
                }else{
                    addToCart.visibility =View.GONE
                }

            }
        })

        retry.setOnClickListener(this)
        addToCart.setOnClickListener(this)

    }

    private fun getProductResponse() {
        progress.visibility = View.VISIBLE
        commonViewModal!!.getProductList().observe(this, Observer { res ->
            run {
                list = res as ArrayList<ProductEntity>
                progress.visibility = View.GONE
                adapter = AdapterClass(applicationContext,list,commonViewModal!!)
                recycler.adapter = adapter
                adapter.notifyDataSetChanged()

            }
        })

    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.retry -> {

                if (isNetworkAvailable()) {
                    noNetwork?.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    getProductResponse()
                } else {
                   Toast.makeText(this,"Please Turn On Internet Connection..",Toast.LENGTH_SHORT).show()
                }
            }

            R.id.addToCart -> {
                val intent = Intent(this,CartActivity::class.java)
                startActivity(intent)
            }
        }
    }
}