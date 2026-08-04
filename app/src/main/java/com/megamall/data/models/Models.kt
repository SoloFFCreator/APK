package com.megamall.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// User Role Enum
enum class UserRole {
    CUSTOMER,
    SELLER,
    DELIVERY_PARTNER,
    ADMINISTRATOR
}

// User Model
data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("role")
    val role: UserRole,
    @SerializedName("profileImage")
    val profileImage: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),
    @SerializedName("isVerified")
    val isVerified: Boolean = false
) : Serializable

// Product Model
data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("originalPrice")
    val originalPrice: Double? = null,
    @SerializedName("category")
    val category: String,
    @SerializedName("sellerId")
    val sellerId: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("reviewCount")
    val reviewCount: Int = 0,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("isFeatured")
    val isFeatured: Boolean = false,
    @SerializedName("createdAt")
    val createdAt: Long = System.currentTimeMillis()
) : Serializable

// Cart Item Model
data class CartItem(
    @SerializedName("productId")
    val productId: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("product")
    val product: Product? = null
) : Serializable

// Order Model
data class Order(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("items")
    val items: List<CartItem>,
    @SerializedName("totalAmount")
    val totalAmount: Double,
    @SerializedName("status")
    val status: OrderStatus,
    @SerializedName("shippingAddress")
    val shippingAddress: String,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("deliveryPartnerId")
    val deliveryPartnerId: String? = null,
    @SerializedName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),
    @SerializedName("updatedAt")
    val updatedAt: Long = System.currentTimeMillis()
) : Serializable

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    PROCESSING,
    SHIPPED,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED,
    RETURNED
}

// Review Model
data class Review(
    @SerializedName("id")
    val id: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("images")
    val images: List<String> = emptyList(),
    @SerializedName("createdAt")
    val createdAt: Long = System.currentTimeMillis()
) : Serializable

// Wishlist Item Model
data class WishlistItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("product")
    val product: Product? = null,
    @SerializedName("addedAt")
    val addedAt: Long = System.currentTimeMillis()
) : Serializable

// Payment Method Model
data class PaymentMethod(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: PaymentType,
    @SerializedName("name")
    val name: String,
    @SerializedName("isDefault")
    val isDefault: Boolean = false
) : Serializable

enum class PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    UPI,
    WALLET,
    CASH_ON_DELIVERY,
    BANK_TRANSFER
}

// Address Model
data class Address(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("zipCode")
    val zipCode: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("isDefault")
    val isDefault: Boolean = false,
    @SerializedName("createdAt")
    val createdAt: Long = System.currentTimeMillis()
) : Serializable

// Category Model
data class Category(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("description")
    val description: String? = null
) : Serializable

// Coupon Model
data class Coupon(
    @SerializedName("id")
    val id: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("discountType")
    val discountType: DiscountType,
    @SerializedName("minAmount")
    val minAmount: Double = 0.0,
    @SerializedName("maxUses")
    val maxUses: Int? = null,
    @SerializedName("expiryDate")
    val expiryDate: Long,
    @SerializedName("isActive")
    val isActive: Boolean = true
) : Serializable

enum class DiscountType {
    PERCENTAGE,
    FIXED_AMOUNT
}

// Notification Model
data class Notification(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("data")
    val data: Map<String, String>? = null,
    @SerializedName("isRead")
    val isRead: Boolean = false,
    @SerializedName("createdAt")
    val createdAt: Long = System.currentTimeMillis()
) : Serializable

// Analytics Data Model
data class AnalyticsData(
    @SerializedName("totalSales")
    val totalSales: Double,
    @SerializedName("totalOrders")
    val totalOrders: Int,
    @SerializedName("totalCustomers")
    val totalCustomers: Int,
    @SerializedName("averageOrderValue")
    val averageOrderValue: Double,
    @SerializedName("conversionRate")
    val conversionRate: Double
) : Serializable

// API Response Wrapper
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: T? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("error")
    val error: String? = null
)
