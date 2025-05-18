<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
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
<main class="flex-1 ml-[300px] mr-[100px] mt-[30px]">
    <h1 class="text-2xl font-bold mb-4 text-center">Danh sách sản phẩm</h1>

    <div class="mb-4 flex justify-between items-center">
        <!-- Nút tạo mới -->
        <a href="/admin/product/create"
           class="bg-primary text-white px-4 py-2 rounded hover:bg-orange-700 transition">
            + Tạo mới
        </a>

        <!-- Form tìm kiếm -->
        <form action="/admin/product/" method="get" class="flex space-x-2">
            <input type="text" name="keyword" placeholder="Tìm theo tên..."
                   class="border rounded p-2 w-64" value="${keyword}" />
            <button type="submit" class="bg-primary text-white px-3 py-2 rounded hover:bg-blue-600">
                Tìm
            </button>
        </form>
    </div>

    <div class="overflow-x-auto">
        <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">Id</th>
                <th class="py-2 px-4 text-left">Tên</th>
                <th class="py-2 px-4 text-left">Giá</th>
                <th class="py-2 px-4 text-left">Danh mục</th>
                <th class="py-2 px-4 text-left">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${products.content}">
                <tr class="border-b hover:bg-gray-50">
                    <td class="py-2 px-4">${p.id}</td>
                    <td class="py-2 px-4">${p.name}</td>
                    <td class="py-2 px-4 text-green-600 font-semibold">${p.price}</td>
                    <td class="py-2 px-4">${p.category.name}</td>
                    <td class="py-2 px-4 space-x-2">
                        <a href="/admin/product/view/${p.id}" class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 text-sm">Xem</a>
                        <a href="/admin/product/update/${p.id}" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600 text-sm">Sửa</a>
                        <form action="/admin/product/delete/${p.id}" method="post" class="inline">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                                    onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                        <a href="/admin/product/add-detail/${p.id}" class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-800 text-sm">Thêm chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- PHÂN TRANG -->
    <div class="mt-6 flex justify-center space-x-2">
        <c:if test="${products.totalPages > 1}">
            <c:forEach var="i" begin="0" end="${products.totalPages - 1}">
                <a href="?page=${i}&keyword=${keyword}"
                   class="px-3 py-1 rounded border
                   <c:if test='${i == products.number}'>bg-primary text-white</c:if>
                   <c:if test='${i != products.number}'>bg-white text-gray-800 hover:bg-gray-200</c:if>">
                        ${i + 1}
                </a>
            </c:forEach>
        </c:if>
    </div>
</main>

</body>
</html>
