<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giới thiệu cửa hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50 text-gray-800 pt-16">
<jsp:include page="../layout/header.jsp" />
<div class="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg">
    <h1 class="text-3xl font-bold text-[#e85205] mb-4">Giới thiệu về H2T</h1>
    <p class="mb-4 text-lg leading-relaxed">
        Chào mừng bạn đến với <strong>H2T</strong> – nơi cung cấp các sản phẩm chất lượng với giá cả hợp lý.
        Chúng tôi luôn nỗ lực mang đến trải nghiệm mua sắm tuyệt vời và dịch vụ khách hàng tận tâm.
    </p>
    <p class="mb-6 text-lg leading-relaxed">
        Với nhiều năm kinh nghiệm trong ngành, H2T tự hào là điểm đến tin cậy của hàng ngàn khách hàng trên khắp cả nước.
    </p>

    <h2 class="text-2xl font-semibold text-[#e85205] mb-2">Địa chỉ cửa hàng</h2>
    <ul class="list-disc list-inside text-lg">
        <li>🏠 Đại Học IT, Quận Nam Từ Liêm, TP. Hà Nội</li>
        <li>📞 Hotline: 0909 123 456</li>
        <li>📧 Email: support@h2t.vn</li>
        <li>🕘 Giờ mở cửa: 8:00 - 21:00 (T2 - CN)</li>
    </ul>

    <div class="mt-6">
        <iframe
                class="w-full h-64 rounded-lg border"
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.509632927746!2d106.70042407573925!3d10.771529989376868!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752ee1747a30b3%3A0xc05462be408731ea!2zMjAzIEzDqiBOZ2jhu4sgVGjDoG5oIFTDom4sIFBoxrDhu51uZyA3LCBRdeG6rW4gMywgVGjDoG5oIHBo4buRIEjhu5MgQ2jDrW5oLCBUUC4gSOG7kyBDaMOtbmg!5e0!3m2!1svi!2s!4v1683612039384!5m2!1svi!2s"
                allowfullscreen=""
                loading="lazy">
        </iframe>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>
