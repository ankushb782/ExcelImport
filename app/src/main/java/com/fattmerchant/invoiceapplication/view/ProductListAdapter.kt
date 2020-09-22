package com.fattmerchant.invoiceapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.model.Data


class ProductListAdapter(private val productList: List<Data>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_product_list, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        if(productList.size>5){
            return 5
        }else
        return productList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name?.text = "L001"
        viewHolder.btnQty?.text = productList[position].qty.toString()
        viewHolder.tvPrice?.text = productList[position].price.toString()

     /*   if(productList[position].qty>0){
            viewHolder.llAddMinus.visibility=View.VISIBLE
            viewHolder.btnAdd.visibility=View.GONE
        }else{
            viewHolder.llAddMinus.visibility=View.GONE
            viewHolder.btnAdd.visibility=View.VISIBLE
        }*/
        viewHolder.itemView.setOnClickListener {
         //   onItemClickListener?.onItemClick(viewHolder.itemView, position,"item")
        }
        viewHolder.btnAdd.setOnClickListener {
            viewHolder.llAddMinus.visibility=View.VISIBLE
            viewHolder.btnAdd.visibility=View.GONE
            onItemClickListener?.onItemClick(viewHolder.itemView, position,"plus")
        }
        viewHolder.btnMinus.setOnClickListener {
            if(productList[position].qty>1)
            onItemClickListener?.onItemClick(viewHolder.itemView, position,"minus")
            else if(productList[position].qty==1){
              //  onItemClickListener?.onItemClick(viewHolder.itemView, position,"minus")
             //   viewHolder.llAddMinus.visibility=View.GONE
            //    viewHolder.btnAdd.visibility=View.VISIBLE
            }
        }
        viewHolder.btnPlus.setOnClickListener {
            onItemClickListener?.onItemClick(viewHolder.itemView, position,"plus")
          //  viewHolder.llAddMinus.visibility=View.VISIBLE
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvName)
        val llAddMinus = itemView.findViewById<LinearLayout>(R.id.llAddMinus)
        val btnAdd = itemView.findViewById<Button>(R.id.btnAdd)
        val btnMinus = itemView.findViewById<Button>(R.id.btnMinus)
        val btnPlus = itemView.findViewById<Button>(R.id.btnPlus)
        val btnQty = itemView.findViewById<TextView>(R.id.btnQty)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int,type:String)
    }
}