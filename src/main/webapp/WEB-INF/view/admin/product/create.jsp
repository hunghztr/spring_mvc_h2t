<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tạo mới</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
<jsp:include page="../layout/side-bar.jsp" />
<main class="ml-[300px] mr-[100px] mt-[30px] p-6 bg-white rounded shadow-md">
    <h1 class="text-2xl font-bold mb-6 text-center">Tạo sản phẩm mới</h1>

    <form:form modelAttribute="product" method="post" action="/admin/product/create"
               enctype="multipart/form-data" class="space-y-6">
        <div>
            <label class="block font-medium mb-1">Tên sản phẩm</label>
            <form:input path="name" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <div>
            <label class="block font-medium mb-1">Số lượng</label>
            <form:input path="quantity" type="number" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <div>
            <label class="block font-medium mb-1">Giá</label>
            <form:input path="price" type="number" step="0.01" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <div>
            <label class="block font-medium mb-1">Mô tả</label>
            <form:textarea path="description" cssClass="w-full border border-gray-300 rounded px-4 py-2" rows="4" />
        </div>

        <!-- Ảnh sản phẩm -->
        <div>
            <label class="block font-medium mb-1">Ảnh sản phẩm</label>
            <input type="file" name="imageFile" class="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <!-- Màu sắc -->
        <div>
            <label class="block font-medium mb-1">Màu sắc</label>
            <form:select path="productDetails.color.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
                <form:option value="" label="-- Chọn màu --" />
                <c:forEach var="s" items="${colors}">
                    <option value="${s.id}">
                         ${s.name}
                    </option>
                </c:forEach>
            </form:select>
        </div>

        <!-- Kích cỡ -->
        <div>
            <label class="block font-medium mb-1">Kích cỡ</label>
            <form:select path="productDetails.size.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
                <form:option value="" label="-- Chọn kích cỡ --" />
                <c:forEach var="s" items="${sizes}">
                    <option value="${s.id}">
                            ${s.name}
                    </option>
                </c:forEach>
            </form:select>
        </div>


        <div>
            <label class="block font-medium mb-1">Thể loại</label>
            <form:select path="category.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
                <form:option value="" label="-- Chọn thể loại --" />
                <c:forEach var="s" items="${categories}">
                    <option value="${s.id}">
                            ${s.name}
                    </option>
                </c:forEach>
            </form:select>
        </div>

        <div class="text-right">
            <button type="submit" class="bg-primary text-white px-6 py-2 rounded hover:bg-orange-700 transition">
                Lưu sản phẩm
            </button>
        </div>
    </form:form>
</main>
</body>
</html>
