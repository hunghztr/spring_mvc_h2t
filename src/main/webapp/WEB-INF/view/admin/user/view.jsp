<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết người dùng</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<jsp:include page="../layout/side-bar.jsp" />

<main class="ml-[300px] mr-[100px] mt-[30px]">
    <h1 class="text-2xl font-bold mb-6 text-center">Chi tiết người dùng</h1>

    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div class="mb-4">
            <strong>ID:</strong> ${user.id}
        </div>
        <div class="mb-4">
            <strong>Tài khoản:</strong> ${user.username}
        </div>
        <div class="mb-4">
            <strong>Địa chỉ:</strong> ${user.address}
        </div>
        <div class="mb-4">
            <strong>Họ và tên:</strong> ${user.fullname}
        </div>
        <div class="mb-4">
            <strong>Vai trò:</strong> ${user.role.name}
        </div>

        <div class="mt-6">
            <a href="/admin/user/update/${user.id}" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 mr-2">Sửa</a>
            <a href="/admin/user/delete/${user.id}" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
               onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?')">Xóa</a>
            <a href="/admin/user/" class="ml-4 text-blue-500 underline">Quay lại danh sách</a>
        </div>
    </div>
</main>
</body>
</html>
