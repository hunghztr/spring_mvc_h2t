<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
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
<body class="min-h-screen bg-white pt-16">
<jsp:include page="../layout/header.jsp" />
<div class = "flex">
<!-- Left Side (Background or Image) -->
<div class="hidden md:flex md:w-1/2 items-center justify-center border-r border-gray-200">
    <!-- Bạn có thể thay khối màu này bằng ảnh -->
    <h1 class="text-white text-4xl font-bold">Đăng Nhập</h1>
</div>

<!-- Right Side (Login Form) -->
<div class="w-full md:w-1/2 flex items-center justify-center p-6">
    <div class="w-full max-w-md bg-white rounded-xl shadow-lg p-8 space-y-6">
        <h2 class="text-3xl font-bold text-center text-gray-800 border-b pb-3 border-gray-200">Đăng Nhập</h2>

        <form:form modelAttribute="user" method="post" action="/login" class="space-y-5">
            <c:if test="${param.error != null}">
                <div class="my-2" style="color: red;">Invalid Email or
                    Password</div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="my-2" style="color: rgb(0, 132, 255);">Logout
                    Success</div>
            </c:if>
            <div>
                <label for="username" class="block text-sm font-medium text-gray-700">Tên đăng nhập</label>
                <form:input path="username"
                            name="username"
                            id="username"
                            cssClass="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" />
            </div>

            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Mật khẩu</label>
                <form:password path="password"
                               name="username"
                               id="password"
                               cssClass="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary" />
            </div>

            <div class="flex items-center justify-between">
                <label class="flex items-center">
                    <input type="checkbox" class="form-checkbox text-primary">
                    <span class="ml-2 text-sm text-gray-600">Ghi nhớ tôi</span>
                </label>
                <a href="#" class="text-sm text-primary hover:underline">Quên mật khẩu?</a>
            </div>

            <button
                    type="submit"
                    class="w-full py-2 px-4 bg-primary text-white font-semibold rounded-lg shadow-md hover:bg-orange-700 transition">
                Đăng nhập
            </button>
            <div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </div>
        </form:form>


        <p class="text-center text-sm text-gray-600">
            Chưa có tài khoản?
            <a href="/register" class="text-primary hover:underline">Đăng ký</a>
        </p>
    </div>
</div>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>
