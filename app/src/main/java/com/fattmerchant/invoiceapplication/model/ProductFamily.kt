package com.fattmerchant.invoiceapplication.model


data class ProductFamily(
    val current_page: Int,
    val `data`: List<Data>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)

data class ReportData(
    val status: String,
    val billno: String,
    val billDate: String,
    val qty: Int,
    val mrp: Double,
    val dis: Double,
    val net: Double
)

data class Data(
    val category: Any,
    val code: Any,
    val created_at: String,
    val deleted_at: Any,
    val details: String="",
    val files: List<Any>,
    val id: String="",
    val in_stock: Int,
    val is_active: Boolean,
    val is_discount: Boolean,
    val is_published: Boolean,
    val is_service: Boolean,
    val is_subscription: Boolean,
    val is_taxable: Boolean,
    val item: String,
    val low_stock_alert: Any,
    val merchant_id: String,
    val meta: MetaD,
    val price: Double=10.0,
    var qty: Int,
    val thumbnail: Any,
    val thumbnail_id: Any,
    val updated_at: String,
    val user: User,
    val user_id: String
)

data class MetaD(
    val is_percentage_discount: Boolean
)

data class User(
    val acknowledgments: Acknowledgments,
    val brand: Any,
    val created_at: String,
    val deleted_at: Any,
    val email: String,
    val email_verification_sent_at: String,
    val email_verified_at: String,
    val gravatar: String,
    val id: String,
    val is_api_key: Boolean,
    val is_default: Boolean,
    val merchant_options: List<Any>,
    val name: String,
    val system_admin: Boolean,
    val team_admin: Any,
    val team_enabled: Any,
    val team_role: Any,
    val updated_at: String
)

data class Acknowledgments(
    val tutorial: Boolean
)