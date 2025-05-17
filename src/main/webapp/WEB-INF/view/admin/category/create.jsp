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
    <h1 class="text-2xl font-bold mb-6 text-center">Tạo thể loại</h1>

    <form:form modelAttribute="category" method="post" action="/admin/category/create" class="space-y-6">
        <div>
            <label class="block font-medium mb-1">Tên thể loại</label>
            <form:input path="name" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <form:select path="sale.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
            <option value="">-- Chọn khuyến mãi --</option>
            <c:forEach var="s" items="${sales}">
                <option value="${s.id}">
                    <c:choose>
                        <c:when test="${s.freeship}">Freeship</c:when>
                        <c:otherwise>Không Freeship</c:otherwise>
                    </c:choose> - ${s.discount}%
                </option>
            </c:forEach>
        </form:select>
        <div class="text-right">
            <button type="submit" class="bg-primary text-white px-6 py-2 rounded hover:bg-orange-700 transition">
                Lưu
            </button>
        </div>
    </form:form>
</main>
</body>
</html>
