<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách khuyến mãi</title>
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
    <h1 class="text-2xl font-bold mb-4 text-center">Danh sách giảm giá</h1>

    <!-- Nút tạo sản phẩm -->
    <div class="mb-4 text-right">
        <a href="/admin/sale/create"
           class="bg-primary text-white px-4 py-2 rounded hover:bg-orange-700 transition">
            + Tạo mới
        </a>
    </div>

    <div class="overflow-x-auto">
        <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">Id</th>
                <th class="py-2 px-4 text-left">Miễn phí ship</th>
                <th class="py-2 px-4 text-left">Giảm giá</th>
                <th class="py-2 px-4 text-left">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${sales}">
                <tr class="border-b hover:bg-gray-50">
                    <td class="py-2 px-4">${p.id}</td>
                    <td class="py-2 px-4">
                        <c:choose>
                            <c:when test="${p.freeship}">Có</c:when>
                            <c:otherwise>Không</c:otherwise>
                        </c:choose>
                    </td>
                    <td class="py-2 px-4">${p.discount}</td>
                    <td class="py-2 px-4">
                        <form action="/admin/sale/delete/${p.id}" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa khuyến mãi này?');">
                            <button type="submit" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 text-sm">
                                Xóa
                            </button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

</body>
</html>
