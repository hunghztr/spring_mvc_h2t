<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="w-64 h-screen text-white fixed flex flex-col shadow-lg">
    <!-- Logo / Title -->
    <div class="p-6 text-2xl font-bold border-b border-orange-400">
        Admin Panel
    </div>

    <!-- Navigation -->
    <ul class="flex-1 p-4 space-y-2 text-base font-medium">
        <li>
            <a href="/admin" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                📊 <span>Thống kê</span>
            </a>
        </li>
        <li>
            <a href="/admin/product/" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                🛍️ <span>Quản lý Sản phẩm</span>
            </a>
        </li>
        <li>
            <a href="/admin/orders" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                📦 <span>Quản lý Đơn hàng</span>
            </a>
        </li>
        <li>
            <a href="/admin/categories" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                🗂️ <span>Danh mục</span>
            </a>
        </li>
        <li>
            <a href="/admin/users" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                👤 <span>Người dùng</span>
            </a>
        </li>
        <li>
            <a href="/admin/sizes" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                📏 <span>Kích thước</span>
            </a>
        </li>
        <li>
            <a href="/admin/colors" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                🎨 <span>Màu sắc</span>
            </a>
        </li>
        <li>
            <a href="/admin/sales" class="flex items-center gap-2 px-3 py-2 rounded hover:bg-orange-600 hover:text-yellow-200 transition">
                💸 <span>Khuyến mãi</span>
            </a>
        </li>
    </ul>

    <!-- Footer -->
    <div class="p-4 text-sm text-orange-100 border-t border-orange-400">
        &copy; 2025 Admin Page
    </div>
</div>
