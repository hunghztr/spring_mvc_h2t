<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="bg-white shadow-md rounded-xl p-8 max-w-md text-center">
    <h1 class="text-3xl font-bold text-red-600 mb-4">Access Denied</h1>
    <p class="text-gray-700 mb-6">Bạn không có quyền truy cập vào trang này.</p>
    <a href="/"
       class="inline-block bg-[#e85205] hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-full transition duration-300">
        Về trang chủ
    </a>
</div>
</body>
</html>
