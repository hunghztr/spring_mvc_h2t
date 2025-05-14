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
<body class="min-h-screen bg-white flex">

<!-- Left Side (Register Form) -->
<div class="w-full md:w-1/2 flex items-center justify-center p-6 border-r border-gray-200">
    <div class="w-full max-w-md bg-white rounded-xl shadow-lg p-8 space-y-6">
        <h2 class="text-3xl font-bold text-center text-gray-800 border-b pb-3 border-gray-200">Đăng Ký</h2>



        <form:form modelAttribute="user" action="/register" method="post" class="space-y-5">
            <div>
                <label class="block text-sm font-medium text-gray-700">Tên đăng nhập</label>
                <form:input path="username" cssClass="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Họ và tên</label>
                <form:input path="fullname" cssClass="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Địa chỉ</label>
                <form:input path="address" cssClass="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Mật khẩu</label>
                <form:password path="password" cssClass="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" />
            </div>

            <div>
                <label class="block text-sm font-medium text-gray-700">Xác nhận mật khẩu</label>
                <input type="password" name="confirm" class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" >
            </div>

            <button
                    type="submit"
                    class="w-full py-2 px-4 bg-primary text-white font-semibold rounded-lg shadow-md hover:bg-orange-700 transition"
            >
                Đăng ký
            </button>
        </form:form>
        <c:if test="${not empty error}">
            <p class="text-red-500 text-sm text-center">${error}</p>
        </c:if>
        <p class="text-center text-sm text-gray-600">
            Đã có tài khoản?
            <a href="/login" class="text-primary hover:underline">Đăng nhập</a>
        </p>
    </div>
</div>

<!-- Right Side -->
<div class="hidden md:flex md:w-1/2 items-center justify-center">
    <h1 class="text-4xl font-bold text-primary">Chào mừng bạn!</h1>
</div>

</body>
</html>
