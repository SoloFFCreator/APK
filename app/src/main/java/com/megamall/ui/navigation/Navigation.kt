package com.megamall.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.megamall.data.models.UserRole

// Navigation routes
sealed class Route(val route: String) {
    // Auth routes
    object Login : Route("login")
    object Register : Route("register")
    object RoleSelection : Route("role_selection")
    
    // Customer routes
    object CustomerHome : Route("customer_home")
    object ProductDetail : Route("product_detail/{productId}")
    object Search : Route("search")
    object Cart : Route("cart")
    object Checkout : Route("checkout")
    object Orders : Route("orders")
    object OrderDetail : Route("order_detail/{orderId}")
    object Wishlist : Route("wishlist")
    object Profile : Route("profile")
    object Reviews : Route("reviews")
    
    // Seller routes
    object SellerDashboard : Route("seller_dashboard")
    object ProductManagement : Route("product_management")
    object AddProduct : Route("add_product")
    object EditProduct : Route("edit_product/{productId}")
    object SellerOrders : Route("seller_orders")
    object SellerAnalytics : Route("seller_analytics")
    object SellerEarnings : Route("seller_earnings")
    object SellerProfile : Route("seller_profile")
    
    // Delivery Partner routes
    object DeliveryDashboard : Route("delivery_dashboard")
    object AvailableOrders : Route("available_orders")
    object DeliveryDetail : Route("delivery_detail/{orderId}")
    object DeliveryTracking : Route("delivery_tracking/{orderId}")
    object DeliveryHistory : Route("delivery_history")
    object DeliveryEarnings : Route("delivery_earnings")
    
    // Admin routes
    object AdminDashboard : Route("admin_dashboard")
    object UserManagement : Route("user_management")
    object ProductModeration : Route("product_moderation")
    object OrderMonitoring : Route("order_monitoring")
    object Reports : Route("reports")
    object CouponManagement : Route("coupon_management")
}

// Bottom navigation item
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int = 0
)

// Navigation for Customer role
fun getCustomerNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem("Home", Icons.Filled.Home, Route.CustomerHome.route),
    BottomNavItem("Search", Icons.Filled.Search, Route.Search.route),
    BottomNavItem("Cart", Icons.Filled.ShoppingCart, Route.Cart.route, badgeCount = 0),
    BottomNavItem("Orders", Icons.Filled.Home, Route.Orders.route),
    BottomNavItem("Profile", Icons.Filled.Person, Route.Profile.route)
)

// Navigation for Seller role
fun getSellerNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem("Dashboard", Icons.Filled.Home, Route.SellerDashboard.route),
    BottomNavItem("Products", Icons.Filled.ShoppingCart, Route.ProductManagement.route),
    BottomNavItem("Orders", Icons.Filled.Home, Route.SellerOrders.route),
    BottomNavItem("Analytics", Icons.Filled.Search, Route.SellerAnalytics.route),
    BottomNavItem("Profile", Icons.Filled.Person, Route.SellerProfile.route)
)

// Navigation for Delivery Partner role
fun getDeliveryNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem("Dashboard", Icons.Filled.Home, Route.DeliveryDashboard.route),
    BottomNavItem("Available", Icons.Filled.ShoppingCart, Route.AvailableOrders.route),
    BottomNavItem("History", Icons.Filled.Home, Route.DeliveryHistory.route),
    BottomNavItem("Earnings", Icons.Filled.Search, Route.DeliveryEarnings.route),
    BottomNavItem("Profile", Icons.Filled.Person, Route.DeliveryDashboard.route)
)

// Navigation for Admin role
fun getAdminNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem("Dashboard", Icons.Filled.Home, Route.AdminDashboard.route),
    BottomNavItem("Users", Icons.Filled.Person, Route.UserManagement.route),
    BottomNavItem("Products", Icons.Filled.ShoppingCart, Route.ProductModeration.route),
    BottomNavItem("Orders", Icons.Filled.Home, Route.OrderMonitoring.route),
    BottomNavItem("Reports", Icons.Filled.Search, Route.Reports.route)
)

// Get navigation items based on user role
fun getNavItemsForRole(role: UserRole): List<BottomNavItem> = when (role) {
    UserRole.CUSTOMER -> getCustomerNavItems()
    UserRole.SELLER -> getSellerNavItems()
    UserRole.DELIVERY_PARTNER -> getDeliveryNavItems()
    UserRole.ADMINISTRATOR -> getAdminNavItems()
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = selectedRoute == item.route,
                onClick = { onItemSelected(item.route) }
            )
        }
    }
}
