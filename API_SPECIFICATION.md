# MegaMall API Specification

## Overview

This document defines the REST API endpoints for the MegaMall e-commerce platform. All endpoints follow RESTful conventions and return JSON responses.

## Base Configuration

- **Base URL:** `https://api.megamall.com/v1`
- **Protocol:** HTTPS
- **Response Format:** JSON
- **Authentication:** JWT Bearer Token

## Authentication

### Login
```
POST /auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}

Response (200):
{
  "success": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs...",
    "user": {
      "id": "user_123",
      "email": "user@example.com",
      "name": "John Doe",
      "role": "CUSTOMER"
    }
  }
}
```

### Register
```
POST /auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "CUSTOMER"
}

Response (201):
{
  "success": true,
  "data": {
    "id": "user_123",
    "email": "john@example.com",
    "name": "John Doe",
    "role": "CUSTOMER"
  }
}
```

### Refresh Token
```
POST /auth/refresh
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIs..."
  }
}
```

## Products

### List Products
```
GET /products?page=1&limit=20&category=electronics&search=headphones
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "products": [
      {
        "id": "prod_123",
        "name": "Wireless Headphones",
        "description": "High-quality wireless headphones",
        "price": 79.99,
        "originalPrice": 99.99,
        "category": "electronics",
        "rating": 4.5,
        "reviewCount": 128,
        "stock": 50,
        "images": ["https://..."]
      }
    ],
    "total": 150,
    "page": 1,
    "limit": 20
  }
}
```

### Get Product Details
```
GET /products/{productId}
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "id": "prod_123",
    "name": "Wireless Headphones",
    "description": "High-quality wireless headphones",
    "price": 79.99,
    "originalPrice": 99.99,
    "category": "electronics",
    "sellerId": "seller_456",
    "rating": 4.5,
    "reviewCount": 128,
    "stock": 50,
    "images": ["https://..."],
    "reviews": [
      {
        "id": "review_789",
        "userId": "user_123",
        "rating": 5,
        "title": "Excellent product",
        "comment": "Great quality and sound"
      }
    ]
  }
}
```

### Create Product (Seller)
```
POST /products
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "New Product",
  "description": "Product description",
  "price": 99.99,
  "category": "electronics",
  "stock": 100,
  "images": ["https://..."]
}

Response (201):
{
  "success": true,
  "data": {
    "id": "prod_new",
    "name": "New Product",
    ...
  }
}
```

### Update Product (Seller)
```
PUT /products/{productId}
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Updated Product",
  "price": 89.99,
  "stock": 80
}

Response (200):
{
  "success": true,
  "data": { ... }
}
```

### Delete Product (Seller)
```
DELETE /products/{productId}
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "message": "Product deleted successfully"
}
```

## Cart

### Get Cart
```
GET /cart
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "items": [
      {
        "productId": "prod_123",
        "quantity": 2,
        "price": 79.99,
        "product": { ... }
      }
    ],
    "subtotal": 159.98,
    "tax": 12.80,
    "total": 172.78
  }
}
```

### Add to Cart
```
POST /cart/items
Authorization: Bearer <token>
Content-Type: application/json

{
  "productId": "prod_123",
  "quantity": 2
}

Response (201):
{
  "success": true,
  "data": { ... }
}
```

### Update Cart Item
```
PUT /cart/items/{itemId}
Authorization: Bearer <token>
Content-Type: application/json

{
  "quantity": 3
}

Response (200):
{
  "success": true,
  "data": { ... }
}
```

### Remove from Cart
```
DELETE /cart/items/{itemId}
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "message": "Item removed from cart"
}
```

## Orders

### Create Order
```
POST /orders
Authorization: Bearer <token>
Content-Type: application/json

{
  "items": [
    {
      "productId": "prod_123",
      "quantity": 2
    }
  ],
  "shippingAddressId": "addr_123",
  "paymentMethodId": "pm_123",
  "couponCode": "SAVE10"
}

Response (201):
{
  "success": true,
  "data": {
    "id": "order_123",
    "userId": "user_123",
    "items": [...],
    "totalAmount": 172.78,
    "status": "PENDING",
    "createdAt": 1690000000000
  }
}
```

### Get Orders
```
GET /orders?status=DELIVERED&page=1&limit=10
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "orders": [
      {
        "id": "order_123",
        "totalAmount": 172.78,
        "status": "DELIVERED",
        "createdAt": 1690000000000
      }
    ],
    "total": 45,
    "page": 1,
    "limit": 10
  }
}
```

### Get Order Details
```
GET /orders/{orderId}
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "id": "order_123",
    "userId": "user_123",
    "items": [...],
    "totalAmount": 172.78,
    "status": "DELIVERED",
    "shippingAddress": {...},
    "paymentMethod": {...},
    "deliveryPartnerId": "dp_456",
    "createdAt": 1690000000000,
    "updatedAt": 1690100000000
  }
}
```

### Update Order Status (Admin/Seller)
```
PUT /orders/{orderId}/status
Authorization: Bearer <token>
Content-Type: application/json

{
  "status": "SHIPPED"
}

Response (200):
{
  "success": true,
  "data": { ... }
}
```

## Payments

### Process Payment
```
POST /payments
Authorization: Bearer <token>
Content-Type: application/json

{
  "orderId": "order_123",
  "paymentMethodId": "pm_123",
  "amount": 172.78,
  "currency": "USD"
}

Response (200):
{
  "success": true,
  "data": {
    "id": "payment_123",
    "orderId": "order_123",
    "amount": 172.78,
    "status": "COMPLETED",
    "transactionId": "txn_123"
  }
}
```

## Reviews

### Create Review
```
POST /reviews
Authorization: Bearer <token>
Content-Type: application/json

{
  "productId": "prod_123",
  "rating": 5,
  "title": "Excellent product",
  "comment": "Great quality and sound"
}

Response (201):
{
  "success": true,
  "data": {
    "id": "review_123",
    "productId": "prod_123",
    "userId": "user_123",
    "rating": 5,
    "title": "Excellent product",
    "comment": "Great quality and sound",
    "createdAt": 1690000000000
  }
}
```

### Get Product Reviews
```
GET /products/{productId}/reviews?page=1&limit=10
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "reviews": [...],
    "total": 128,
    "page": 1,
    "limit": 10
  }
}
```

## Wishlist

### Get Wishlist
```
GET /wishlist
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "items": [
      {
        "id": "wish_123",
        "productId": "prod_123",
        "product": { ... },
        "addedAt": 1690000000000
      }
    ]
  }
}
```

### Add to Wishlist
```
POST /wishlist
Authorization: Bearer <token>
Content-Type: application/json

{
  "productId": "prod_123"
}

Response (201):
{
  "success": true,
  "data": { ... }
}
```

### Remove from Wishlist
```
DELETE /wishlist/{itemId}
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "message": "Item removed from wishlist"
}
```

## Addresses

### Get Addresses
```
GET /addresses
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "addresses": [
      {
        "id": "addr_123",
        "fullName": "John Doe",
        "street": "123 Main St",
        "city": "New York",
        "state": "NY",
        "zipCode": "10001",
        "country": "USA",
        "isDefault": true
      }
    ]
  }
}
```

### Create Address
```
POST /addresses
Authorization: Bearer <token>
Content-Type: application/json

{
  "fullName": "John Doe",
  "phone": "+1234567890",
  "street": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001",
  "country": "USA",
  "isDefault": false
}

Response (201):
{
  "success": true,
  "data": { ... }
}
```

## Seller Analytics

### Get Sales Analytics
```
GET /seller/analytics/sales?startDate=2026-01-01&endDate=2026-07-31
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "totalSales": 15420.50,
    "totalOrders": 234,
    "averageOrderValue": 65.85,
    "conversionRate": 3.45,
    "topProducts": [...]
  }
}
```

## Delivery Partner

### Get Available Orders
```
GET /delivery/orders/available?latitude=40.7128&longitude=-74.0060&radius=5
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "orders": [
      {
        "id": "order_123",
        "customerName": "John Doe",
        "address": "123 Main St, New York",
        "distance": 2.5,
        "earnings": 5.50
      }
    ]
  }
}
```

### Accept Delivery
```
POST /delivery/orders/{orderId}/accept
Authorization: Bearer <token>

Response (200):
{
  "success": true,
  "data": {
    "id": "order_123",
    "status": "OUT_FOR_DELIVERY",
    "deliveryPartnerId": "dp_456"
  }
}
```

## Error Handling

### Error Response Format
```json
{
  "success": false,
  "error": "Invalid request",
  "message": "Email is required",
  "code": "VALIDATION_ERROR"
}
```

### Common Error Codes
- `VALIDATION_ERROR` (400) - Invalid input data
- `UNAUTHORIZED` (401) - Missing or invalid authentication
- `FORBIDDEN` (403) - Insufficient permissions
- `NOT_FOUND` (404) - Resource not found
- `CONFLICT` (409) - Resource already exists
- `INTERNAL_ERROR` (500) - Server error

## Rate Limiting

- **Limit:** 1000 requests per hour
- **Header:** `X-RateLimit-Remaining`
- **Reset:** `X-RateLimit-Reset`

## Pagination

All list endpoints support pagination:
- `page` - Page number (default: 1)
- `limit` - Items per page (default: 20, max: 100)

---

**Last Updated:** July 31, 2026
