<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: '#e85205'
                    }
                }
            }
        }
    </script>
</head>
<body class="">
<jsp:include page="../layout/side-bar.jsp" />
<div class="ml-64 p-8">
    <h1 class="text-3xl font-bold mb-6">Tổng Quan Hệ Thống</h1>

    <div class="grid grid-cols-4 gap-6 mb-8">
        <!-- 1 -->
        <div class="bg-white rounded shadow p-4">
            <p class="text-gray-500">Số lượng sản phẩm</p>
            <h2 class="text-2xl font-bold">0</h2>
        </div>

        <!-- 2 -->
        <div class="bg-[#e85205] rounded shadow p-4 text-white">
            <p class="text-white opacity-80">Số đơn hàng</p>
            <h2 class="text-2xl font-bold">0</h2>
        </div>

        <!-- 3 -->
        <div class="bg-white rounded shadow p-4">
            <p class="text-gray-500">Tổng doanh thu</p>
            <h2 class="text-2xl font-bold text-green-600">0đ</h2>
        </div>

        <!-- 4 -->
        <div class="bg-[#e85205] rounded shadow p-4 text-white">
            <p class="text-white opacity-80">Số người dùng</p>
            <h2 class="text-2xl font-bold">0</h2>
        </div>
    </div>


    <!-- Có thể thêm biểu đồ ở đây nếu cần -->
</div>

</body>
</html>
