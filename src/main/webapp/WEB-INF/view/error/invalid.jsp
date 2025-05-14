<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lỗi hệ thống</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-gray-100 flex items-center justify-center">
<div class="max-w-lg w-full bg-white rounded-lg shadow-md p-6">
    <h1 class="text-2xl font-bold text-red-600 text-center">Đã có lỗi xảy ra</h1>
    <p class="mt-4 text-center text-gray-700">Chúng tôi xin lỗi vì sự bất tiện này. Vui lòng thử lại sau.</p>
    <div class="mt-6 text-center">
        <h2 class="text-xl font-semibold text-gray-800">Thông báo lỗi:</h2>
        <p class="text-red-500 text-lg mt-2">${message}</p> <!-- Hiển thị thông báo lỗi từ controller -->
    </div>
    <div class="mt-6 text-center">
        <a href="/home" class="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-700">Quay lại trang chủ</a>
    </div>
</div>
</body>
</html>
