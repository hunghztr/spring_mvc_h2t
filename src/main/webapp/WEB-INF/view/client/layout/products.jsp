<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<section class="bg-gray-900 text-white py-10">
    <div class="max-w-7xl mx-auto px-4">
        <h2 class="text-3xl font-bold mb-8 text-center">SẢN PHẨM MỚI</h2>
        <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            <c:forEach var="product" items="${products}">
                <div class="bg-gray-800 p-4 rounded-lg shadow">
                    <!-- Ảnh chính -->
                    <c:forEach var="img" items="${product.images}" varStatus="loop">
                        <c:if test="${loop.index == 0}">
                            <img src="/images/${img.name}" alt="${product.name}"
                                 class="w-full h-60 object-cover rounded">
                        </c:if>
                    </c:forEach>
                    <!-- Tên sản phẩm -->
                    <h3 class="mt-4 text-sm font-semibold line-clamp-1">${product.name}</h3>

                    <!-- Ảnh phụ -->
                    <div class="flex space-x-1 mt-2">
                        <c:forEach var="subImage" items="${product.images}">
                            <img src="/images/${subImage}"
                                 class="w-6 h-6 object-cover border border-white rounded-sm">
                        </c:forEach>
                    </div>

                    <!-- Giá -->
                    <p class="mt-2 text-sm font-bold text-white">${product.price}₫</p>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
