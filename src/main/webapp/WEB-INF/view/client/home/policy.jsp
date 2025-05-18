<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chính sách mua hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50 text-gray-800 pt-16">
<jsp:include page="../layout/header.jsp" />
<div class="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-lg">
    <h1 class="text-3xl font-bold text-[#e85205] mb-6">Chính sách mua hàng</h1>

    <h2 class="text-xl font-semibold text-gray-800 mb-2">1. Chính sách đổi/trả hàng</h2>
    <p class="mb-4 text-lg leading-relaxed">
        Khách hàng có thể đổi/trả sản phẩm trong vòng <strong>7 ngày</strong> kể từ ngày nhận hàng nếu sản phẩm bị lỗi kỹ thuật hoặc không đúng mô tả.
    </p>

    <h2 class="text-xl font-semibold text-gray-800 mb-2">2. Chính sách vận chuyển</h2>
    <p class="mb-4 text-lg leading-relaxed">
        - Giao hàng toàn quốc qua các đơn vị vận chuyển uy tín.<br>
        - Miễn phí vận chuyển cho đơn hàng từ <strong>500.000đ</strong> trở lên.
    </p>

    <h2 class="text-xl font-semibold text-gray-800 mb-2">3. Chính sách thanh toán</h2>
    <p class="mb-4 text-lg leading-relaxed">
        MyShop hỗ trợ các hình thức thanh toán: <br>
        - Thanh toán khi nhận hàng (COD)<br>
        - Thanh toán qua ví điện tử (VNPay, Momo...)
    </p>

    <h2 class="text-xl font-semibold text-gray-800 mb-2">4. Hỗ trợ khách hàng</h2>
    <p class="mb-2 text-lg leading-relaxed">
        Nếu bạn cần hỗ trợ, vui lòng liên hệ:
    </p>
    <ul class="list-disc list-inside text-lg">
        <li>Hotline: 0909 123 456</li>
        <li>Email: support@h2t.vn</li>
        <li>Giờ làm việc: 8:00 – 21:00 (Thứ 2 đến Chủ nhật)</li>
    </ul>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>
