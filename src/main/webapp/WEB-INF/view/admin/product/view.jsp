<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<jsp:include page="../layout/side-bar.jsp" />

<main class="ml-[300px] mr-[100px] mt-[30px]">
    <h1 class="text-2xl font-bold mb-6 text-center">Chi tiết sản phẩm</h1>

    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div class="mb-4">
            <strong>ID:</strong> ${product.id}
        </div>
        <div class="mb-4">
            <strong>Tên:</strong> ${product.name}
        </div>
        <div class="mb-4">
            <strong>Mô tả:</strong> ${product.description}
        </div>
        <div class="mb-4">
            <strong>Giá:</strong> <span class="text-green-600 font-semibold">${product.price}</span>
        </div>
        <div class="mb-4">
            <strong>Danh mục:</strong> ${product.category.name}
        </div>

        <div class="mb-4">
            <strong>Hình ảnh:</strong><br/>
            <c:if test="${not empty product.images}">
                <div class="flex flex-wrap gap-4 mt-2">
                    <c:forEach var="img" items="${product.images}">
                        <img src="/images/products/${img.name}" class="w-24 h-24 object-cover rounded shadow" alt="Ảnh sản phẩm"/>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty product.images}">
                <span class="text-red-500">Không có ảnh</span>
            </c:if>
        </div>
        <c:if test="${not empty details}">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4">
                <c:forEach var="p" items="${details}">
                    <c:if test="${not empty p.color or not empty p.size}">
                        <div class="border rounded-xl shadow-md p-4 bg-white">
                            <p><span class="font-semibold">Màu sắc:</span> <c:out value="${p.color.name}" /></p>
                            <p><span class="font-semibold">Kích cỡ:</span> <c:out value="${p.size.name}" /></p>
                            <p><span class="font-semibold">Số lượng:</span> <c:out value="${p.total}" /></p>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:if>



        <div class="mb-4">
            <strong>Ngày tạo:</strong>
            <fmt:formatDate value="${createdAt}" pattern="dd/MM/yyyy HH:mm:ss"/>
        </div>
        <div class="mb-4">
            <strong>Ngày cập nhật:</strong>
            <fmt:formatDate value="${updatedAt}" pattern="dd/MM/yyyy HH:mm:ss"/>
        </div>

        <div class="mt-6">
            <a href="/admin/product/update/${product.id}" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 mr-2">Sửa</a>
            <a href="/admin/product/delete/${product.id}" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
               onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?')">Xóa</a>
            <a href="/admin/product/" class="ml-4 text-blue-500 underline">Quay lại danh sách</a>
        </div>
    </div>
</main>
</body>
</html>
