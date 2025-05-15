<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header class="bg-white shadow-md">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center py-4">

            <!-- Logo -->
            <div class="text-xl font-bold text-primary">
                MyShop
            </div>

            <!-- Navigation Menu -->
            <nav class="hidden md:flex space-x-8 text-gray-700 font-medium">
                <a href="#" class="hover:text-primary">Sản phẩm</a>
                <a href="#" class="hover:text-primary">Combo đồng giá</a>
                <a href="#" class="hover:text-primary">Địa chỉ</a>
                <a href="#" class="hover:text-primary">Chính sách</a>
            </nav>

            <!-- Right Section: Search, Cart, Avatar -->
            <div class="flex items-center space-x-4">
                <!-- Search -->
                <div class="relative">
                    <input
                            type="text"
                            placeholder="Tìm kiếm..."
                            class="pl-3 pr-10 py-2 border rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary"
                    />
                    <svg
                            class="w-5 h-5 absolute right-2 top-2.5 text-gray-400"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="2"
                            viewBox="0 0 24 24"
                    >
                        <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-4.35-4.35M11 18a7 7 0 1 1 0-14 7 7 0 0 1 0 14z" />
                    </svg>
                </div>

                <!-- Cart Icon -->
                <a href="#" class="relative text-gray-700 hover:text-primary">
                    <svg
                            class="w-6 h-6"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="2"
                            viewBox="0 0 24 24"
                    >
                        <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13l-1.5 7h11l-1.5-7M7 13h10"
                        />
                    </svg>
                    <span class="absolute -top-2 -right-2 bg-red-500 text-white text-xs rounded-full px-1">0</span>
                </a>

                <!-- Avatar -->
                <div>
                    <a href="/login"
                       class="text-sm font-medium text-gray-700 hover:text-primary px-4 py-2 rounded-md border border-gray-300 hover:border-primary transition">
                        Đăng nhập
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>