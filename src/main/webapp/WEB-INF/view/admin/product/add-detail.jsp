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
    <h1 class="text-2xl font-bold mb-6 text-center">Tạo chi tiết sản phẩm mới</h1>

    <form:form modelAttribute="detail" method="post" action="/admin/product/add-detail"
               enctype="multipart/form-data" class="space-y-6">
        <div>
            <label class="block font-medium mb-1">Tên sản phẩm</label>
            <input class="hidden w-full border border-gray-300 rounded px-4 py-2" name="id" value="${product.id}">
            <input cssClass="w-full border border-gray-300 rounded px-4 py-2" value="${product.name}" readonly>
        </div>

        <div>
            <label class="block font-medium mb-1">Số lượng</label>
            <form:input path="total" type="number" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <div>
            <label class="block font-medium mb-1">Màu sắc</label>
            <form:select path="color.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
                <form:option value="" label="-- Chọn màu --" />
                <c:forEach var="s" items="${colors}" varStatus="loop">
                    <option value="${s.id}" ${loop.index == 0 ? 'selected' : ''}>
                            ${s.name}
                    </option>
                </c:forEach>
            </form:select>
        </div>
        <div>
            <label class="block font-medium mb-1">Kích cỡ</label>
            <form:select path="size.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
                <form:option value="" label="-- Chọn kích cỡ --" />
                <c:forEach var="s" items="${sizes}" varStatus="loop">
                    <option value="${s.id}" ${loop.index == 0 ? 'selected' : ''}>
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
