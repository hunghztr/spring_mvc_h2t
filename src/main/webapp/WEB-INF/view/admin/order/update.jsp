<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật đơn hàng</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<jsp:include page="../layout/side-bar.jsp" />

<main class="ml-[300px] mr-[100px] mt-[30px]">
    <h1 class="text-2xl font-bold mb-6 text-center">Cập nhật đơn hàng</h1>

    <form:form action="/admin/order/update" method="post" modelAttribute="order" class="space-y-4 bg-white p-6 rounded shadow-md">
        <div>
            <label class="block font-semibold mb-1">ID</label>
            <form:input path="id" cssClass="w-full border rounded p-2" readonly="true" />
        </div>
        <div>
            <label class="block font-semibold mb-1">Tổng tiền</label>
            <form:input path="total" cssClass="w-full border rounded p-2" readonly="true" />
        </div>

        <div>
            <label class="block font-semibold mb-1">Ghi chú</label>
            <form:textarea path="note" cssClass="w-full border rounded p-2" rows="3" />
        </div>

        <div>
            <label class="block font-semibold mb-1">Phương thức thanh toán</label>
            <form:select path="method" cssClass="w-full border rounded p-2">
                <form:option value="COD" label="Thanh toán khi nhận hàng" />
                <form:option value="VNPAY" label="Thanh toán VNPAY" />
            </form:select>
        </div>

        <div>
            <label class="block font-semibold mb-1">Trạng thái</label>
            <label class="flex items-center space-x-2">
                <form:checkbox path="completed" cssClass="w-5 h-5" />
                <span class="text-lg font-semibold">Hoàn thành</span>
            </label>
        </div>

        <div>
            <h2 class="text-lg font-semibold mb-2">Danh sách sản phẩm</h2>
            <ul class="list-disc list-inside space-y-1">
                <c:forEach var="product" items="${products}">
                    <li>
                        <span class="font-medium">${product.name}</span>
                        –
                        <span class="text-gray-600">
                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫" />
                </span>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="pt-4">
            <button type="submit" class="bg-[#e85205] text-white px-4 py-2 rounded hover:bg-blue-700">
                Cập nhật đơn hàng
            </button>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form:form>
</main>

</body>
</html>
