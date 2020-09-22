package com.fattmerchant.invoiceapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.view.ProductListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, ProductListFragment()).commit()
    }
}
