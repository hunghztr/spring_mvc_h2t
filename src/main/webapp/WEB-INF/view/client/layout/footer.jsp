<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<footer class="bg-gray-100 text-gray-700 mt-16 border-t border-gray-200">
    <div class="max-w-7xl mx-auto px-4 py-10 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8">

        <!-- Cột 1: Thông tin chung -->
        <div>
            <h3 class="text-lg font-semibold mb-4">Về MyShop</h3>
            <ul class="space-y-2 text-sm">
                <li><a href="#" class="hover:text-primary">Giới thiệu</a></li>
                <li><a href="#" class="hover:text-primary">Tuyển dụng</a></li>
                <li><a href="#" class="hover:text-primary">Tin tức</a></li>
            </ul>
        </div>

        <!-- Cột 2: Hỗ trợ khách hàng -->
        <div>
            <h3 class="text-lg font-semibold mb-4">Hỗ trợ</h3>
            <ul class="space-y-2 text-sm">
                <li><a href="#" class="hover:text-primary">Hướng dẫn mua hàng</a></li>
                <li><a href="#" class="hover:text-primary">Câu hỏi thường gặp</a></li>
                <li><a href="#" class="hover:text-primary">Liên hệ hỗ trợ</a></li>
            </ul>
        </div>

        <!-- Cột 3: Chính sách -->
        <div>
            <h3 class="text-lg font-semibold mb-4">Chính sách</h3>
            <ul class="space-y-2 text-sm">
                <li><a href="#" class="hover:text-primary">Chính sách đổi trả</a></li>
                <li><a href="#" class="hover:text-primary">Chính sách bảo mật</a></li>
                <li><a href="#" class="hover:text-primary">Chính sách vận chuyển</a></li>
            </ul>
        </div>

        <!-- Cột 4: Liên hệ -->
        <div>
            <h3 class="text-lg font-semibold mb-4">Liên hệ</h3>
            <p class="text-sm mb-2">Hotline: <span class="font-medium">1900 123 456</span></p>
            <p class="text-sm mb-2">Email: support@myshop.vn</p>
            <div class="flex space-x-4 mt-2">
                <!-- Social -->
                <a href="#" class="hover:text-primary">
                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                        <path d="M22 12.07C22 6.48 17.52 2 12 2S2 6.48 2 12.07c0 5 3.66 9.13 8.44 9.88v-6.99H7.9v-2.89h2.54V9.41c0-2.51 1.49-3.89 3.77-3.89 1.09 0 2.24.2 2.24.2v2.46h-1.26c-1.24 0-1.63.77-1.63 1.56v1.87h2.77l-.44 2.89h-2.33v6.99C18.34 21.2 22 17.07 22 12.07z" />
                    </svg>
                </a>
                <a href="#" class="hover:text-primary">
                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
                        <path d="M22.54 6.42a8.45 8.45 0 0 1-2.44.67 4.3 4.3 0 0 0 1.87-2.38 8.59 8.59 0 0 1-2.72 1.05A4.28 4.28 0 0 0 16.11 4c-2.37 0-4.29 1.9-4.29 4.25 0 .33.04.66.11.97-3.56-.18-6.72-1.86-8.83-4.42a4.19 4.19 0 0 0-.58 2.14c0 1.48.76 2.78 1.92 3.55a4.28 4.28 0 0 1-1.94-.53v.05c0 2.07 1.49 3.8 3.46 4.2a4.3 4.3 0 0 1-1.93.07 4.28 4.28 0 0 0 4 2.95A8.6 8.6 0 0 1 2 19.54 12.14 12.14 0 0 0 8.29 21c7.55 0 11.69-6.16 11.69-11.5 0-.18-.01-.35-.02-.52a8.28 8.28 0 0 0 2.04-2.1z" />
                    </svg>
                </a>
            </div>
        </div>
    </div>

    <!-- Copyright -->
    <div class="bg-gray-200 text-center text-sm text-gray-600 py-4">
        © 2025 MyShop. Đã đăng ký bản quyền.
    </div>
</footer>
