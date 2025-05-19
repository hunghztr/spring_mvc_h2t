<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: '#e85205'
                    }
                }
            }
        }
    </script>
</head>
<body class="flex flex-col min-h-screen pt-16">
<jsp:include page="../layout/header.jsp"/>
<main class="flex-grow">

    <section class="bg-[#111] text-white py-10 px-4">
        <div class="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-2 gap-10">
            <!-- Ảnh sản phẩm -->
            <div class="flex flex-col md:flex-row gap-4">
                <!-- Danh sách ảnh nhỏ -->
                <div class="flex md:flex-col gap-2 overflow-auto max-h-[500px] md:max-h-none">
                    <c:forEach var="img" items="${product.images}">
                        <img src="/images/products/${img.name}" alt="${product.name}"
                             class="w-20 h-20 object-cover rounded border border-gray-600 hover:border-[#e85205] cursor-pointer"
                             onclick="changeMainImage('/images/products/${img.name}')"/>
                    </c:forEach>
                </div>

                <!-- Ảnh chính -->
                <div class="flex-1">
                    <img id="mainImage" src="/images/products/${product.images[0].name}" alt="${product.name}"
                         class="w-full object-cover rounded shadow-md"/>
                </div>
            </div>


            <!-- Thông tin sản phẩm -->
            <div>
                <c:if test="${not empty message}">
                    <div class="text-red-600 font-medium mt-4">
                            ${message}
                    </div>
                </c:if>
                <h1 class="text-2xl font-semibold mb-2">${product.name}</h1>
                <form action="/add-cart" method="get">
                    <input value="${product.id}" name="productId" class="hidden">
                    <div class="mb-4">
                        <c:choose>
                            <c:when test="${product.category.sale.discount != 0}">
                                <!-- Giá gốc bị gạch nếu có giảm -->
                                <p class="line-through text-gray-400 text-sm">
                                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫"
                                                      maxFractionDigits="0"/>
                                </p>
                                <!-- Giá sau giảm -->
                                <p class="text-[#e85205] text-xl font-bold">
                                    <fmt:formatNumber
                                            value="${product.price * (1 - (product.category.sale.discount / 100.0))}"
                                            type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <!-- Giá không gạch nếu không có giảm -->
                                <p class="text-[#e85205] text-xl font-bold">
                                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫"
                                                      maxFractionDigits="0"/>
                                </p>
                            </c:otherwise>
                        </c:choose>

                        <!-- Hiển thị miễn phí vận chuyển nếu có -->
                        <c:if test="${product.category.sale.freeship}">
                            <p class="text-green-500 text-sm mt-1 font-medium">Miễn phí vận chuyển</p>
                        </c:if>
                    </div>


                    <!-- Size -->
                    <div class="mb-4" id="sizeSelector">
                        <p class="font-medium mb-1">Chọn size:</p>
                        <div class="flex gap-2">
                            <c:forEach var="size" items="${sizes}" varStatus="sizeStatus">
                                <label class="cursor-pointer border border-gray-500 px-3 py-1 rounded hover:border-[#e85205] flex items-center">
                                    <input
                                            type="radio"
                                            name="size"
                                            value="${size.name}"
                                            class="hidden peer"
                                        ${sizeStatus.index == 0 ? "checked required" : ""}
                                    />
                                    <span class="peer-checked:border-[#e85205] peer-checked:text-[#e85205]">
            <c:out value="${size.name}"/>
        </span>
                                </label>
                            </c:forEach>

                        </div>
                    </div>


                    <!-- Màu -->
                    <div class="mb-4" id="colorSelector">
                        <p class="font-medium mb-1">Màu sắc:</p>
                        <div class="flex gap-2">
                            <c:forEach var="color" items="${colors}" varStatus="status">
                                <label class="cursor-pointer border border-gray-500 px-3 py-1 rounded hover:border-[#e85205] flex items-center">
                                    <input
                                            type="radio"
                                            name="color"
                                            value="${color.name}"
                                            class="hidden peer"
                                        ${status.index == 0 ? "checked required" : ""}
                                    />
                                    <span class="peer-checked:border-[#e85205] peer-checked:text-[#e85205]">
            <c:out value="${color.name}"/>
        </span>
                                </label>
                            </c:forEach>
                        </div>
                    </div>

                    <p class="font-medium mb-1">Số lượng:</p>
                    <!-- Nút hành động -->
                    <div class="flex items-center gap-4 mt-6">
                        <button id="btnDecrease"
                                type="button"
                                class="border border-gray-400 px-4 py-2 rounded hover:border-[#e85205] hover:text-[#e85205]">
                            -
                        </button>

                        <!-- Hiển thị số lượng -->
                        <input
                                type="number"
                                id="quantity"
                                name="count"
                                value="1"
                                min="1"
                                class="font-semibold text-lg text-center w-16 border rounded"
                        >
                        <button id="btnIncrease" type="button" class="bg-[#e85205] text-white px-4 py-2 rounded hover:bg-opacity-80">
                            +
                        </button>
                    </div>


                    <button id="btnAddToCart"
                            class="bg-[#e85205] mt-6 text-white px-6 py-2 rounded hover:bg-opacity-80 inline-block text-center">
                        Thêm vào giỏ hàng
                    </button>

                </form>

                <!-- Mô tả -->
                    <div class="mt-8">
                        <h2 class="text-lg font-semibold mb-2">Mô tả</h2>
                        <p class="text-sm leading-relaxed">
                            ${product.description}
                        </p>
                    </div>
            </div>
        </div>
    </section>
    <section class="bg-white py-10 px-4">
        <div class="max-w-7xl mx-auto">
            <h2 class="text-2xl font-semibold mb-6 text-gray-800">Sản phẩm liên quan</h2>
            <div class="grid grid-cols-2 md:grid-cols-5 gap-6">
                <c:forEach var="p" items="${suggestions}">
                    <div class="border rounded shadow hover:shadow-lg transition">
                        <a href="/product/${p.id}">
                            <img src="/images/products/${p.images[0].name}" alt="${p.name}"
                                 class="w-full h-40 object-cover rounded-t"/>
                            <div class="p-3">
                                <p class="font-medium truncate">${p.name}</p>
                                <div class="text-[#e85205] font-medium">
                                    <c:choose>
                                        <c:when test="${p.category.sale.discount != 0}">
                                            <!-- Giá gốc bị gạch nếu có giảm -->
                                            <p class="line-through text-gray-400 text-sm mb-1">
                                                <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫"
                                                                  maxFractionDigits="0"/>
                                            </p>
                                            <!-- Giá sau giảm -->
                                            <p class="text-[#e85205] text-xl font-bold m-0">
                                                <fmt:formatNumber
                                                        value="${p.price * (1 - (p.category.sale.discount / 100.0))}"
                                                        type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                            </p>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Giá không gạch nếu không có giảm -->
                                            <p class="text-[#e85205] text-xl font-bold m-0">
                                                <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫"
                                                                  maxFractionDigits="0"/>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

</main>
<script>
    function changeMainImage(newSrc) {
        const mainImg = document.getElementById("mainImage");
        if (mainImg) {
            mainImg.src = newSrc;
        }
    }
</script>

<script>
    let quantity = 0;

    const btnIncrease = document.getElementById('btnIncrease');
    const btnDecrease = document.getElementById('btnDecrease');
    const quantitySpan = document.getElementById('quantity');

    btnIncrease.addEventListener('click', () => {
        quantity++;
        quantitySpan.value = quantity;
    });

    btnDecrease.addEventListener('click', () => {
        if (quantity > 1) {
            quantity--;
            quantitySpan.value = quantity;
        }
    });
</script>

<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
