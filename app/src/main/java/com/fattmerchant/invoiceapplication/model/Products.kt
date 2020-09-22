package com.fattmerchant.invoiceapplication.model


data class Products(
    val balance_due: Int,
    val child_transactions: List<Any>,
    val created_at: String,
    val customer: Customer,
    val customer_id: String,
    val deleted_at: Any,
    val files: List<Any>,
    val id: String,
    val is_webpayment: Boolean,
    val merchant_id: String,
    val paid_at: Any,
    val payment_attempt_failed: Boolean,
    val payment_attempt_message: String,
    val payment_meta: List<Any>,
    val payment_method_id: String,
    val reminder: Any,
    val reminder_id: Any,
    val schedule_id: Any,
    val sent_at: Any,
    val status: String,
    val total: Int,
    val total_paid: Int,
    val updated_at: String,
    val url: String,
    val user: User,
    val user_id: String,
    val viewed_at: Any
)

data class Customer(
    val address_1: String,
    val address_2: String,
    val address_city: String,
    val address_country: String,
    val address_state: String,
    val address_zip: String,
    val cc_emails: List<String>,
    val company: String,
    val created_at: String,
    val deleted_at: Any,
    val email: String,
    val firstname: String,
    val gravatar: String,
    val id: String,
    val lastname: String,
    val notes: Any,
    val options: String,
    val phone: String,
    val reference: String,
    val updated_at: String
)

data class MetaData(
    val subtotal: Int,
    val tax: Int
)

data class UserData(
    val created_at: String,
    val deleted_at: Any,
    val email: String,
    val email_verification_sent_at: String,
    val email_verified_at: String,
    val gravatar: String,
    val id: String,
    val is_api_key: Boolean,
    val name: String,
    val system_admin: Boolean,
    val team_admin: Any,
    val team_enabled: Any,
    val team_role: Any,
    val updated_at: String
)

data class LineItemData(
    val details: String,
    val id: String,
    val item: String,
    val price: String,
    val quantity: Int
)