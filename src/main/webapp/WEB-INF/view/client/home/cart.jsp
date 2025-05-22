<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <c:if test="${not empty products}">
            <c:forEach var="i" begin="0" end="${fn:length(products) - 1}">
                <c:set var="item" value="${products[i]}"/>
                <c:set var="quantity" value="${totals[i]}"/>
                <c:set var="s" value="${sizes[i]}"/>
                <c:set var="c" value="${colors[i]}"/>

                <!-- Cart Item -->
                <div class="flex gap-6 border-b border-gray-700 pb-6 mb-6">
                    <!-- Product Image -->
                    <img src="/images/products/${item.images[0].name}" alt="${item.name}"
                         class="w-28 h-32 object-cover rounded-lg shadow-md"/>

                    <!-- Product Info -->
                    <div class="flex-1 flex flex-col justify-between">
                        <div>
                            <h2 class="font-semibold text-lg text-white mb-1">${item.name}</h2>

                            <!-- Discount Logic -->
                            <c:set var="discount" value="${item.category.sale.discount}"/>
                            <c:set var="hasDiscount" value="${discount != null && discount > 0}"/>
                            <c:set var="finalPrice" value="${item.price - (item.price * discount / 100)}"/>

                            <!-- Price Display -->
                            <div class="mb-1">
                                <c:choose>
                                    <c:when test="${hasDiscount}">
                                        <p class="text-sm text-gray-500 line-through">
                                            <fmt:formatNumber value="${item.price}" type="currency"
                                                              maxFractionDigits="0"/>
                                        </p>
                                        <p class="text-orange-500 font-semibold text-base">
                                            <fmt:formatNumber value="${finalPrice}" type="currency"
                                                              maxFractionDigits="0"/>
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-base text-gray-300 font-medium">
                                            <fmt:formatNumber value="${item.price}" type="currency"
                                                              maxFractionDigits="0"/>
                                        </p>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <!-- Size / Color -->
                            <p class="text-sm text-gray-400 mb-2">${s.name} / ${c.name}</p>
                        </div>

                        <!-- Quantity -->
                        <form action="update-cart" method="post" class="flex items-center gap-2">
                            <input type="hidden" name="productId" value="${item.id}"/>
                            <button type="button" name="action" value="decrease" id="btnDecrease"
                                    class="w-8 h-8 flex items-center justify-center border border-gray-500 rounded hover:border-orange-500 hover:text-orange-500 text-lg">
                                -
                            </button>

                            <span class="w-8 text-center text-white" id="count">${quantity}</span>

                            <button type="button" name="action" value="increase" id="btnIncrease"
                                    class="w-8 h-8 flex items-center justify-center border border-gray-500 rounded hover:border-orange-500 hover:text-orange-500 text-lg">
                                +
                            </button>
                        </form>
                    </div>

                    <!-- Price + Remove -->
                    <div class="flex flex-col justify-between items-end min-w-[100px]">
                        <!-- Remove Button -->
                        <form action="/remove-cart" method="post" class="flex flex-col justify-between h-full">
                            <button type="submit" class="text-2xl text-gray-400 hover:text-red-500 self-end">&times;
                            </button>


                            <!-- Discounted Total -->
                            <c:choose>
                                <c:when test="${hasDiscount}">
                                    <c:set var="discountedTotal" value="${finalPrice * quantity}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="discountedTotal" value="${item.price * quantity}"/>
                                </c:otherwise>
                            </c:choose>

                            <!-- Total Price -->
                            <p class="text-lg text-orange-500 font-semibold mt-auto">
                                <fmt:formatNumber value="${discountedTotal}" type="currency" maxFractionDigits="0"/>
                            </p>
                            <!-- Hidden Inputs -->
                            <input type="number" class="hidden" name="count" value="${quantity}"/>
                            <input type="hidden" name="price" value="${discountedTotal}"/>
                            <input type="hidden" name="productId" value="${item.id}"/>
                            <input type="hidden" name="size" value="${s.name}"/>
                            <input type="hidden" name="color" value="${c.name}"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:if>

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
        <p class="text-sm text-gray-400 mb-4">Phí vận chuyển sẽ được tính ở trang thanh toán.<br>Bạn cũng có thể nhập mã
            giảm giá ở trang thanh toán.</p>
        <form action="/checkout" method="get">
            <button type="submit"
                    class="w-full bg-red-600 hover:bg-red-700 text-white font-semibold py-2 rounded">
                THANH TOÁN
            </button>
        </form>
        <a href="/" class="text-sm text-orange-500 hover:underline block mt-3 text-center">
            ← Tiếp tục mua hàng
        </a>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>

<script>
    let quantity = 0;
    const btnIncrease = document.getElementById('btnIncrease');
    const btnDecrease = document.getElementById('btnDecrease');
    const quantitySpan = document.getElementById('count');

    quantity = parseInt(quantitySpan.textContent);

    btnIncrease?.addEventListener('click', () => {
        quantity++;
        quantitySpan.textContent = quantity;
    });

    btnDecrease?.addEventListener('click', () => {
        if (quantity > 1) {
            quantity--;
            quantitySpan.textContent = quantity;
        }
    });
</script>
</body>
</html>
