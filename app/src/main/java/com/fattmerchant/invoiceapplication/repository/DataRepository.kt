package com.fattmerchant.invoiceapplication

import com.fattmerchant.invoiceapplication.model.ProductFamily
import retrofit2.Call
import retrofit2.Response

class DataRepository(val netWorkApi: NetWorkApi) {

    fun getProducts(onProductData: OnProductData) {
        netWorkApi.getProducts().enqueue(object : retrofit2.Callback<ProductFamily> {
            override fun onResponse(call: Call<ProductFamily>, response: Response<ProductFamily>) {
                onProductData.onSuccess((response.body() as ProductFamily))
            }

            override fun onFailure(call: Call<ProductFamily>, t: Throwable) {
                onProductData.onFailure()
            }
        })
    }

    interface OnProductData {
        fun onSuccess(data: ProductFamily)
        fun onFailure()
    }
}

