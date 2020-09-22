package com.fattmerchant.invoiceapplication.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fattmerchant.invoiceapp.ProductViewModel
import com.fattmerchant.invoiceapplication.R
import com.fattmerchant.invoiceapplication.async
import com.fattmerchant.invoiceapplication.model.*
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.VerticalAlignment
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.charts.AxisCrosses
import org.apache.poi.ss.usermodel.charts.AxisPosition
import org.apache.poi.ss.usermodel.charts.DataSources
import org.apache.poi.ss.usermodel.charts.LegendPosition
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFChart
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.koin.android.viewmodel.ext.android.viewModel
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.io.FileOutputStream

class ReportListFragment : Fragment() {


    var listProducts =  mutableListOf<ReportData>()
    var btnStart:Button?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.fragment_report_list, container, false)
        btnStart=view.findViewById(R.id.start)
        btnStart?.setOnClickListener {
            if (!EasyPermissions.hasPermissions(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                EasyPermissions.requestPermissions(
                    this,
                    "Needed for the demo",
                    2,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                return@setOnClickListener
            }

            async { generateExportIntent() }.addOnSuccessListener {
                startActivity(it)
            }.addOnFailureListener {
                Log.e("SpreadsheetExporter", "Export failed", it)
                Toast.makeText(requireActivity(), it.stackTrace.toList().toString(), Toast.LENGTH_LONG).show()
            }
        }
        return view
    }


    @SuppressLint("WrongConstant")
    override fun onStart() {
        super.onStart()


        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)

    var reportData=ReportData("","","",4,4.0,4.0,4.0)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        listProducts.add(reportData)
        var productListAdapter: ReportListAdapter = ReportListAdapter(listProducts)
        recyclerView.adapter = productListAdapter


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        btnStart?.performClick()
    }

    private fun generateExportIntent(): Intent {
        val file = File(
            "${Environment.getExternalStorageDirectory()}/Documents/poi-android-demo.xlsx")

        if (file.exists()) file.delete()

        val workbook: Workbook = XSSFWorkbook()
        workbook.createSheet("Test").populate()
        workbook.write(FileOutputStream(file))

        val uri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", file)
        return Intent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .setAction(Intent.ACTION_VIEW)
            .setDataAndType(uri, "application/vnd.ms-excel")
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            .also {
                if(it.resolveActivity(requireContext().packageManager)==null) {
                    it.setDataAndType(uri, "*/*")
                }
            }
    }

    /**
     * Add random stuff to a spreadsheet to make sure everything works
     */
    private fun Sheet.populate() {
        createFreezePane(1, 1)
        createRow(0).apply {
            createCell(0).apply {
                setCellValue("Date")
               
            }
            createCell(1).apply {
                setCellValue("Qty")
            }
            createCell(2).apply {
             setCellValue("Amount")

            }
        }
        for(i in 0 until listProducts.size){
            createRow(i+1).apply {
                createCell(0).apply {
                    setCellValue(listProducts.get(i).billDate)

                }
                createCell(1).apply {
                    setCellValue(listProducts.get(i).net)
                }
                createCell(2).apply {
                    setCellValue(listProducts.get(i).mrp)
                }
            }
        }
       

    }

    private fun CTTitle.setValue(text: String) {
        addNewLayout()
        addNewOverlay().`val` = false

        val textBody = addNewTx().addNewRich()
        textBody.addNewBodyPr()
        textBody.addNewLstStyle()

        val paragraph = textBody.addNewP()
        paragraph.addNewPPr().addNewDefRPr()
        paragraph.addNewR().t = text
        paragraph.addNewEndParaRPr()
    }

    companion object {
        init {
            System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLInputFactory",
                "com.fasterxml.aalto.stax.InputFactoryImpl"
            )
            System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLOutputFactory",
                "com.fasterxml.aalto.stax.OutputFactoryImpl"
            )
            System.setProperty(
                "org.apache.poi.javax.xml.stream.XMLEventFactory",
                "com.fasterxml.aalto.stax.EventFactoryImpl"
            )
        }
    }



}
