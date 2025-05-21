<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
<body class="bg-gray-50">

<jsp:include page="../layout/side-bar.jsp" />

<div class="ml-64 p-8">
    <h1 class="text-3xl font-bold mb-6">Tổng Quan Hệ Thống</h1>

    <div class="grid grid-cols-4 gap-6 mb-10">
        <!-- Tổng sản phẩm -->
        <div class="bg-white rounded shadow p-4">
            <p class="text-gray-500">Số lượng sản phẩm</p>
            <h2 class="text-2xl font-bold">${products}</h2>
        </div>

        <!-- Đơn hàng -->
        <div class="bg-[#e85205] rounded shadow p-4 text-white">
            <p class="opacity-80">Số đơn hàng</p>
            <h2 class="text-2xl font-bold">${orders}</h2>
        </div>

        <!-- Doanh thu -->
        <div class="bg-white rounded shadow p-4">
            <p class="text-gray-500">Tổng doanh thu</p>
            <h2 class="text-2xl font-bold text-green-600">
                <fmt:formatNumber value="${totalPrice}" type="currency" maxFractionDigits="0"/>
            </h2>
        </div>

        <!-- Người dùng -->
        <div class="bg-[#e85205] rounded shadow p-4 text-white">
            <p class="opacity-80">Số người dùng</p>
            <h2 class="text-2xl font-bold">${users}</h2>
        </div>
    </div>

    <!-- Biểu đồ -->
    <div class="bg-white rounded shadow p-6">
        <h2 class="text-xl font-semibold mb-4">Biểu đồ số lượng sản phẩm theo danh mục</h2>
        <canvas id="productChart" class="w-full h-80"></canvas>
    </div>
</div>

<script>
    const ctx = document.getElementById('productChart').getContext('2d');
    const chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [<c:forEach var="cat" items="${categoryNames}" varStatus="loop">
                "<c:out value='${cat}'/>"<c:if test="${!loop.last}">,</c:if>
                </c:forEach>],
            datasets: [{
                label: 'Số lượng sản phẩm',
                data: [<c:forEach var="count" items="${categoryCounts}" varStatus="loop">
                    ${count}<c:if test="${!loop.last}">,</c:if>
                    </c:forEach>],
                backgroundColor: '#e85205'
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>

</body>
</html>
