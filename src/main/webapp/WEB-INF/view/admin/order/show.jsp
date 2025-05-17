<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng</title>
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
<body class="">
<jsp:include page="../layout/side-bar.jsp" />
<main class="flex-1 ml-[300px] mr-[100px] mt-[30px]">
    <h1 class="text-2xl font-bold mb-4 text-center">Danh sách đơn hàng</h1>

    <div class="mb-4 flex justify-between items-center">

        <!-- Form tìm kiếm -->
        <form action="/admin/order/" method="get" class="flex space-x-2">
            <input type="text" name="keyword" placeholder="Tìm theo mã..."
                   class="border rounded p-2 w-64" value="${keyword}" />
            <button type="submit" class="bg-primary text-white px-3 py-2 rounded hover:bg-blue-600">
                Tìm
            </button>
        </form>
    </div>
    <div class="overflow-x-auto">
        <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">Id</th>
                <th class="py-2 px-4 text-left">Tổng tiền</th>
                <th class="py-2 px-4 text-left">Trạng thái</th>
                <th class="py-2 px-4 text-left">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${orders.content}">
                <tr class="border-b hover:bg-gray-50">
                    <td class="py-2 px-4">${p.id}</td>
                    <td class="py-2 px-4">${p.price}</td>
                    <td class="py-2 px-4 font-semibold">
                        <c:choose>
                            <c:when test="${p.completed}">
                                <span class="text-green-600">Hoàn thành</span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-red-500">Chưa hoàn thành</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="py-2 px-4 space-x-2">
                        <a href="/admin/order/update/${p.id}" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600 text-sm">Sửa</a>
                        <form action="/admin/order/delete/${p.id}" method="post" class="inline">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                                    onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- PHÂN TRANG -->
    <div class="mt-6 flex justify-center space-x-2">
        <c:if test="${orders.totalPages > 1}">
            <c:forEach var="i" begin="0" end="${orders.totalPages - 1}">
                <a href="?page=${i}&keyword=${keyword}"
                   class="px-3 py-1 rounded border
                   <c:if test='${i == orders.number}'>bg-primary text-white</c:if>
                   <c:if test='${i != orders.number}'>bg-white text-gray-800 hover:bg-gray-200</c:if>">
                        ${i + 1}
                </a>
            </c:forEach>
        </c:if>
    </div>
</main>

</body>
</html>
