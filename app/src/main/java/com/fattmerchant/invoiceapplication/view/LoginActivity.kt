package com.fattmerchant.invoiceapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.view.MainActivity2
import com.fattmerchant.invoiceapplication.view.ProductListFragment
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity2::class.java)
         //   intent.putExtra("Username","John Doe")
            startActivity(intent)
        }
    }
}
