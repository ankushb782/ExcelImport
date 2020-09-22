package com.fattmerchant.invoiceapplication.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapp.ProductViewModel
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val productListModel: ProductViewModel by viewModel()

    var listProducts =  mutableListOf<Data>()
    var listDetailProducts =  mutableListOf<Data>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_productcart_list, container, false)
    }


    @SuppressLint("WrongConstant")
    override fun onStart() {
        super.onStart()

        val tvPlaceOrder = view?.findViewById<TextView>(R.id.tvPlaceOrder)
        val tvTotalPrice = view?.findViewById<TextView>(R.id.tvTotalPrice)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)


        val recyclerViewItemDetails = view?.findViewById<RecyclerView>(R.id.recyclerViewItemDetails)
        recyclerViewItemDetails!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)
        var productListAdapter: ProductListAdapter = ProductListAdapter(listProducts)
        recyclerView.adapter = productListAdapter


        productListAdapter.setItemClickListener(object : ProductListAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int,type:String) {
                if (type.equals("plus")) {
                    productListModel.addItems(position)

                }else if(type.equals("minus")){
                    productListModel.minusItems(position)
                }
                productListAdapter.notifyItemChanged(position)


            }
        })


        productListModel.listOfProducts.observe(this, Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(productList:List<Data>?) {
            productList?.let {
                listProducts.clear()
                listProducts.addAll(productList)

                productListAdapter.notifyDataSetChanged()


            }
        }))

        productListModel.listOfProductDetail.observe(this, Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(productList:List<Data>?) {
            productList?.let {
                listDetailProducts.clear()
                listDetailProducts.addAll(productList)

               // productDetailAdapter.notifyDataSetChanged()


            }
        }))



        productListModel.totalAmount.observe(this, Observer(function = @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.N)
        fun(amount:Double?) {
            amount?.let {
                tvTotalPrice?.text="$"+amount
            }
        }))

        tvPlaceOrder?.setOnClickListener {


          //  invoiceListModel.createInvoice(createInovieRequest(productListModel.listOfProducts.value!!))
        }

        productListModel.getProducts()
    }






}
