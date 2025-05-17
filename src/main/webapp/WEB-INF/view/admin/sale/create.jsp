<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tạo khuyến mãi</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen">
<jsp:include page="../layout/side-bar.jsp" />
<main class="ml-[300px] mr-[100px] mt-[30px] p-6 bg-white rounded shadow-md">
    <h1 class="text-2xl font-bold mb-6 text-center">Tạo khuyến mãi</h1>

    <form:form modelAttribute="sale" method="post" action="/admin/sale/create" class="space-y-6">
        <div>
            <label class="block font-medium mb-1">Miễn phí vận chuyển?</label>
            <form:checkbox path="freeship" cssClass="mr-2" />
            <span class="text-gray-700">Có</span>
        </div>

        <div>
            <label class="block font-medium mb-1">Giảm giá (%)</label>
            <form:input path="discount" type="number" min="0" max="100" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <div class="text-right">
            <button type="submit" class="bg-primary text-white px-6 py-2 rounded hover:bg-orange-700 transition">
                Lưu khuyến mãi
            </button>
        </div>
    </form:form>
</main>
</body>
</html>
