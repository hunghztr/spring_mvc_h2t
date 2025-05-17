<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách thể loại</title>
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
    <h1 class="text-2xl font-bold mb-4 text-center">Danh sách thể loại</h1>


    <div class="mb-4 text-right">
        <a href="/admin/category/create"
           class="bg-primary text-white px-4 py-2 rounded hover:bg-orange-700 transition">
            + Tạo mới
        </a>
    </div>

    <div class="overflow-x-auto">
        <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead class="bg-gray-200">
            <tr>
                <th class="py-2 px-4 text-left">Id</th>
                <th class="py-2 px-4 text-left">Tên</th>
                <th class="py-2 px-4 text-left">Khuyến mãi</th>
                <th class="py-2 px-4 text-left">Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${categories}">
                <tr class="border-b hover:bg-gray-50">
                    <td class="py-2 px-4">${p.id}</td>
                    <td class="py-2 px-4">${p.name}</td>
                    <td class="py-2 px-4">
                        <c:choose>
                            <c:when test="${p.sale != null}">
                                ${p.sale.freeship ? 'Miễn phí ship, ' : ''}${p.sale.discount}%
                            </c:when>
                            <c:otherwise>
                                Không có
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="py-2 px-4 space-x-2">
                        <a href="/admin/category/update/${p.id}" class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600">Sửa</a>

                        <form action="/admin/category/delete/${p.id}" method="post" class="inline">
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

</main>

</body>
</html>
