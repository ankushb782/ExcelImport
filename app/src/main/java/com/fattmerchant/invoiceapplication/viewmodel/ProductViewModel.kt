package com.fattmerchant.invoiceapp


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fattmerchant.invoiceapplication.DataRepository
import com.fattmerchant.invoiceapplication.model.Data
import com.fattmerchant.invoiceapplication.model.ProductFamily
import org.koin.standalone.KoinComponent


class ProductViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {

    var listOfProducts = MutableLiveData<List<Data>>()
    var listOfProductDetail = MutableLiveData<List<Data>>()
    var totalAmount = MutableLiveData<Double>()

    init {
        listOfProducts.value = listOf()
        listOfProductDetail.value = listOf()
    }

    fun getProducts() {
        dataRepository.getProducts(object : DataRepository.OnProductData {
            override fun onSuccess(data: ProductFamily) {
                var listOfProduct = mutableListOf<Data>()
                listOfProduct.addAll(data.data)
                listOfProducts.value = listOfProduct

            }

            override fun onFailure() {
                //REQUEST FAILED
            }
        })
    }

    fun addItems(position: Int){
        listOfProducts.value!!.get(position).qty=listOfProducts.value!!.get(position).qty+1
        updateItemDetails()
        updateCart()
    }
    fun minusItems(position: Int){
        listOfProducts.value!!.get(position).qty=listOfProducts.value!!.get(position).qty-1
        updateItemDetails()
        updateCart()
    }

    fun updateCart() {
        var count=0.0
        for (i in 0 until listOfProducts.value!!.size ){
            count=count+(listOfProducts.value!!.get(i).qty*listOfProducts.value!!.get(i).price)
        }
        totalAmount.value=count


    }

    fun updateItemDetails(){
        var listDetails= mutableListOf<Data>()
        for (i in 0 until listOfProducts.value!!.size ){
            if(listOfProducts.value!!.get(i).qty>0){
                listDetails.add(listOfProducts.value!!.get(i))

            }

        }
        listOfProductDetail.value=listDetails
    }

    fun resetAll(){
        var listDetails= mutableListOf<Data>()
        for (i in 0 until listOfProducts.value!!.size ){
            listOfProducts.value!!.get(i).qty=0
                listDetails.add(listOfProducts.value!!.get(i))

        }
        listOfProducts.value=listDetails
        updateItemDetails()
        updateCart()
    }
}