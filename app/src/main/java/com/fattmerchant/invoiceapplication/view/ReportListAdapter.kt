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
import com.fattmerchant.invoiceapplication.model.ReportData


class ReportListAdapter(private val productList: List<ReportData>) :
    RecyclerView.Adapter<ReportListAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_report_list, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int,type:String)
    }
}