<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black text-white px-8 py-10 font-sans pt-16">
<jsp:include page="../layout/header.jsp"/>
<!-- Tiêu đề -->
<div class="text-center mb-10 mt-10">
    <h1 class="text-3xl font-bold">Giỏ hàng của bạn</h1>
    <c:choose>
        <c:when test="${cart.total > 0}">
            <p class="text-gray-400 mt-2">Có ${cart.total} sản phẩm trong giỏ hàng</p>
        </c:when>
        <c:otherwise>
            <p class="text-gray-400 mt-2">Giỏ hàng của bạn đang trống</p>
        </c:otherwise>
    </c:choose>

</div>

<!-- Layout giỏ hàng -->
<div class="flex flex-col lg:flex-row gap-8">

    <!-- Danh sách sản phẩm -->
    <div class="flex-1">
        <c:forEach var="item" items="${products}">
            <div class="flex gap-4 border-b border-gray-700 pb-4 mb-4">
                <!-- Ảnh -->
                <img src="/images/products/${item.images[0].name}" alt="${item.name}" class="w-28 h-32 object-cover rounded">

                <!-- Thông tin -->
                <div class="flex-1">
                    <h2 class="font-semibold text-lg">${item.name}</h2>
                    <p class="text-gray-400 mt-1"><fmt:formatNumber value="${item.price}" type="currency"/></p>
<%--                    <p class="mt-1 text-sm text-gray-300">${item.productDetails.size.name} / ${item.productDetails.color.name}</p>--%>

                    <!-- Số lượng -->
                    <div class="flex items-center mt-2">
                        <form action="update-cart" method="post" class="flex items-center">
                            <input type="hidden" name="productId" value="${item.id}" />
                            <button type="submit" name="action" value="decrease"
                                    class="border px-3 py-1 hover:border-orange-500 hover:text-orange-500">-</button>
                            <span class="px-4">số sp</span>
                            <button type="submit" name="action" value="increase"
                                    class="border px-3 py-1 hover:border-orange-500 hover:text-orange-500">+</button>
                        </form>
                    </div>
                </div>

                <!-- Giá + xóa -->
                <div class="flex flex-col items-end justify-between">
                    <form action="remove-cart" method="post">
                        <input type="hidden" name="productId" value="${item.id}" />
                        <button type="submit" class="text-xl hover:text-red-500">&times;</button>
                    </form>
<%--                    <p class="text-lg"><fmt:formatNumber value="${item.price * item.quantity}" type="currency"/></p>--%>
                </div>
            </div>
        </c:forEach>

        <!-- Ghi chú -->
        <div class="mb-6">
            <p class="font-semibold mb-2">Ghi chú đơn hàng</p>
            <textarea placeholder="Ghi chú"
                      class="w-full bg-gray-900 border border-gray-600 p-3 rounded text-white placeholder-gray-400"
                      name="note"></textarea>
        </div>

        <!-- Chính sách -->
        <div>
            <p class="font-semibold mb-2">Chính sách mua hàng</p>
            <ul class="text-sm text-gray-300 space-y-1 list-disc list-inside">
                <li>Sản phẩm được đổi 1 lần duy nhất, không hỗ trợ trả.</li>
                <li>Sản phẩm còn đủ tem mác, chưa qua sử dụng.</li>
                <li>Sản phẩm nguyên giá được đổi trong 30 ngày trên toàn hệ thống.</li>
                <li>Sản phẩm sale chỉ hỗ trợ đổi size (nếu cửa hàng còn) trong 7 ngày trên toàn hệ thống.</li>
            </ul>
        </div>
    </div>

    <!-- Thông tin đơn hàng -->
    <div class="w-full lg:w-1/3 bg-gray-900 p-6 rounded">
        <h2 class="text-xl font-bold mb-4">Thông tin đơn hàng</h2>
        <div class="flex justify-between text-lg font-semibold mb-2">
            <span>Tổng tiền:</span>
            <span class="text-red-500"><fmt:formatNumber value="${total}" type="currency"/></span>
        </div>
        <p class="text-sm text-gray-400 mb-4">Phí vận chuyển sẽ được tính ở trang thanh toán.<br>Bạn cũng có thể nhập mã giảm giá ở trang thanh toán.</p>
        <form action="checkout" method="post">
            <button type="submit"
                    class="w-full bg-red-600 hover:bg-red-700 text-white font-semibold py-2 rounded">
                THANH TOÁN
            </button>
        </form>
        <a href="shop" class="text-sm text-orange-500 hover:underline block mt-3 text-center">
            ← Tiếp tục mua hàng
        </a>
    </div>

</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
