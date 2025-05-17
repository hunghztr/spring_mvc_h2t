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
    <h1 class="text-2xl font-bold mb-6 text-center">Cập nhật người dùng</h1>

    <form:form modelAttribute="user" method="post" action="/admin/user/update" class="space-y-6">
        <div>
            <form:input path="id" cssClass="hidden w-full border border-gray-300 rounded px-4 py-2" />
        </div>
        <div>
            <c:set var="usernameError">
                <form:errors path="username" class="text-red-600" />
            </c:set>
            <label class="block font-medium mb-1">Tài khoản</label>
            <form:input path="username" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
                ${usernameError}
        </div>
        <div>
            <label class="block font-medium mb-1">Địa chỉ</label>
            <form:input path="address" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>
        <div>
            <label class="block font-medium mb-1">Họ và tên</label>
            <form:input path="fullname" cssClass="w-full border border-gray-300 rounded px-4 py-2" />
        </div>

        <div>
            <label class="block font-medium mb-1">Vai trò</label>
            <form:select path="role.id" cssClass="w-full border border-gray-300 rounded px-4 py-2">
                <form:option value="" label="-- Chọn vai trò --" />
                <c:forEach var="s" items="${roles}">
                    <option value="${s.id}" <c:if test="${user.role != null && s.id == user.role.id}">selected</c:if>>
                            ${s.name}
                    </option>
                </c:forEach>
            </form:select>
        </div>
        <p class = "text-red-600">${roleError}</p>
        <div class="text-right">
            <button type="submit" class="bg-primary text-white px-6 py-2 rounded hover:bg-orange-700 transition">
                Lưu
            </button>
        </div>
    </form:form>
</main>
</body>
</html>
