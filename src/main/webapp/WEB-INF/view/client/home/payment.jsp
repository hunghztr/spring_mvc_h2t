<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thanh toán</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-black text-white px-8 py-10 font-sans pt-16">
<jsp:include page="../layout/header.jsp"/>
<div class="min-h-screen bg-gray-900 text-white font-sans py-8 px-4 md:px-16 lg:px-32">
    <h1 class="text-2xl font-bold mb-6">H2TSHOP</h1>

<form action="/checkout" method="post">
    <div class="grid md:grid-cols-3 gap-10">
        <!-- Left: Form -->
        <div class="md:col-span-2 space-y-6">
            <h2 class="text-xl font-semibold">Thông tin giao hàng</h2>


            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <input type="text" placeholder="Họ và tên" value="${sessionScope.fullname}"
                       class="bg-gray-800 text-white px-4 py-2 rounded border border-gray-700 w-full" name="fullname"/>
                <input type="text" placeholder="Email"
                       class="bg-gray-800 text-white px-4 py-2 rounded border border-gray-700 w-full" name="email"/>
            </div>

            <div class="space-y-4">

                <textarea rows="2" placeholder="Địa chỉ" name="address"
                          class="w-full bg-gray-800 text-white px-4 py-2 rounded border border-gray-700">${sessionScope.address}
                </textarea>
                <textarea rows="2" placeholder="Ghi chú" name="note"
                          class="w-full bg-gray-800 text-white px-4 py-2 rounded border border-gray-700"></textarea>

                <!-- Phương thức thanh toán -->
                <div class="space-y-2">
                    <h3 class="text-lg font-semibold">Phương thức thanh toán</h3>
                    <div class="flex items-center gap-2">
                        <input type="radio" name="paymentMethod" value="COD" checked class="accent-blue-500"/>
                        <label>Thanh toán khi nhận hàng (COD)</label>
                    </div>
                    <div class="flex items-center gap-2">
                        <input type="radio" name="paymentMethod" value="VNPAY" class="accent-blue-500"/>
                        <label>Thanh toán qua VNPay</label>
                    </div>
                </div>

            </div>

            <button class="bg-[#e85205] hover:text-[#e85205] hover:bg-gray-900 text-white font-semibold px-6 py-2 rounded
    transition duration-300 ease-in-out">
                Tiếp tục đến phương thức thanh toán
            </button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </div>

        <!-- Right: Order summary -->
        <div class="bg-gray-800 p-6 rounded shadow space-y-4">
            <c:forEach var="item" items="${list}">
                <div class="flex gap-4">
                    <div class="relative">
                        <div class="w-20 h-24 flex items-center justify-center bg-white rounded">
                            <img src="/images/products/${item.item1.product.images[0].name}" alt="product"
                                 class="max-w-full max-h-full object-contain"/>
                        </div>
                        <span class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full px-1">${item.item3}</span>
                    </div>
                    <div>
                        <h3 class="font-semibold">${item.item1.product.name}</h3>
                        <p class="text-sm text-gray-400">${item.item1.size.name} / ${item.item1.color.name}</p>
                    </div>
                    <div class="ml-auto font-semibold">
                        <fmt:formatNumber
                                value="${(item.item1.product.price - item.item1.product.price * item.item2.discount / 100) * item.item3}"
                                type="currency"
                                currencySymbol="₫"
                                maxFractionDigits="0"/>
                    </div>

                </div>
            </c:forEach>

        </div>
    </div>
</form>
    <jsp:include page="../layout/footer.jsp"/>


</body>
</html>
