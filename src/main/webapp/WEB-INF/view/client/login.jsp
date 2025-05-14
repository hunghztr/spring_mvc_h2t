<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<body class="min-h-screen bg-white flex">

<!-- Left Side (Background or Image) -->
<div class="hidden md:flex md:w-1/2 bg-primary items-center justify-center">
    <!-- Bạn có thể thay khối màu này bằng ảnh -->
    <h1 class="text-white text-4xl font-bold">Đăng Nhập</h1>
</div>

<!-- Right Side (Login Form) -->
<div class="w-full md:w-1/2 flex items-center justify-center p-6">
    <div class="w-full max-w-md bg-white rounded-xl shadow-lg p-8 space-y-6">
        <h2 class="text-3xl font-bold text-center text-gray-800">Đăng Nhập</h2>

        <form action="login" method="post" class="space-y-5">
            <div>
                <label for="username" class="block text-sm font-medium text-gray-700">Tên đăng nhập</label>
                <input
                        type="text"
                        id="username"
                        name="username"
                        required
                        class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary"
                >
            </div>

            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Mật khẩu</label>
                <input
                        type="password"
                        id="password"
                        name="password"
                        required
                        class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-primary"
                >
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
                    class="w-full py-2 px-4 bg-primary text-white font-semibold rounded-lg shadow-md hover:bg-orange-700 transition"
            >
                Đăng nhập
            </button>
        </form>

        <p class="text-center text-sm text-gray-600">
            Chưa có tài khoản?
            <a href="register.jsp" class="text-primary hover:underline">Đăng ký</a>
        </p>
    </div>
</div>

</body>
</html>
