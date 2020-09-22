package com.fattmerchant.invoiceapplication


import com.fattmerchant.invoiceapplication.model.ProductFamily
import com.fattmerchant.invoiceapplication.model.Products
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetWorkApi{
    @GET("item/")
    fun getProducts(): Call<ProductFamily>



}