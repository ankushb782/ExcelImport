package com.fattmerchant.invoiceapplication

import com.fattmerchant.invoiceapplication.model.Products
import retrofit2.Call
import retrofit2.Response

class DataRepositoryPost(val netWorkApi: NetWorkApi) {


    interface OnProductData {
        fun onSuccess(data: Products)
        fun onFailure()
    }
}

