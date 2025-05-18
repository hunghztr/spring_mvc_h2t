<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<section class="bg-[#111] py-10 px-4 text-white">
    <h2 class="text-3xl font-bold text-center mb-8">SẢN PHẨM MỚI</h2>
    <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-6 max-w-7xl mx-auto">
        <c:forEach var="product" items="${products.content}">
            <div class="bg-[#1c1c1c] p-4 rounded shadow hover:shadow-lg transition duration-300 group overflow-hidden">
                <div class="relative w-full h-60 mb-3">
                    <c:forEach var="img" items="${product.images}" varStatus="status">
                        <c:if test="${status.index == 0}">
                            <img src="/images/products/${img.name}"
                                 alt="${product.name}"
                                 class="w-full h-full object-cover rounded transition-opacity duration-300 group-hover:opacity-0"/>
                        </c:if>
                        <c:if test="${status.index == 1}">
                            <img src="/images/products/${img.name}"
                                 alt="${product.name}"
                                 class="w-full h-full object-cover rounded absolute top-0 left-0 opacity-0 group-hover:opacity-100 transition-opacity duration-300"/>
                        </c:if>
                    </c:forEach>
                </div>

                <h3 class="font-semibold text-sm mb-1 truncate hover:text-[#e85205]">
                    <a href="/product/${product.id}" class="">
                            ${product.name}
                    </a>
                </h3>
                <p class="text-[#e85205] font-medium">
                    <c:choose>
                    <c:when test="${product.category.sale.discount != 0}">
                    <!-- Giá gốc bị gạch nếu có giảm -->
                <p class="line-through text-gray-400 text-sm">
                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                </p>
                <!-- Giá sau giảm -->
                <p class="text-[#e85205] text-xl font-bold">
                    <fmt:formatNumber value="${product.price * (1 - (product.category.sale.discount / 100.0))}"
                                      type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                </p>
                </c:when>
                <c:otherwise>
                    <!-- Giá không gạch nếu không có giảm -->
                    <p class="text-[#e85205] text-xl font-bold">
                        <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                    </p>
                </c:otherwise>
                </c:choose>
                </p>
            </div>
        </c:forEach>

    </div>
    <!-- PHÂN TRANG -->
    <div class="mt-6 flex justify-center space-x-2">
        <c:if test="${products.totalPages > 1}">
            <c:forEach var="i" begin="0" end="${products.totalPages - 1}">
                <a href="?page=${i}&keyword=${keyword}"
                   class="px-3 py-1 rounded border
                   <c:if test='${i == products.number}'>bg-primary text-white</c:if>
                   <c:if test='${i != products.number}'>bg-white text-gray-800 hover:bg-gray-200</c:if>">
                        ${i + 1}
                </a>
            </c:forEach>
        </c:if>
    </div>
</section>

