<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
<jsp:include page="../layout/side-bar.jsp" />
<main class="ml-[300px] mr-[100px] mt-[30px] p-6 bg-white rounded shadow-md">
    <h1 class="text-2xl font-bold mb-6 text-center">Cập nhật thể loại</h1>

    <form:form modelAttribute="category" method="post" action="/admin/category/update" class="space-y-6">
        <div class = "hidden">
            <form:input path="id" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>
        <div class = "hidden">
            <form:input path="createdAt" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>
        <!-- Tên thể loại -->
        <div>
            <label class="block font-medium mb-1">Tên thể loại</label>
            <form:input path="name" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <!-- Chọn khuyến mãi -->
        <div>
            <label class="block font-medium mb-1">Khuyến mãi</label>
            <select name="sale.id" class="w-full border border-gray-300 rounded px-4 py-2">
                <option value="">-- Chọn khuyến mãi --</option>
                <c:forEach var="s" items="${sales}" varStatus="loop">
                    <option value="${s.id}" <c:if test="${category.sale != null && s.id == category.sale.id}">selected</c:if>>
                        <c:choose>
                            <c:when test="${s.freeship}">Freeship</c:when>
                            <c:otherwise>Không Freeship</c:otherwise>
                        </c:choose> - ${s.discount}%
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Nút lưu -->
        <div class="text-right">
            <button type="submit" class="bg-primary text-white px-6 py-2 rounded hover:bg-orange-700 transition">
                Lưu
            </button>
        </div>

    </form:form>

</main>
</body>
</html>
