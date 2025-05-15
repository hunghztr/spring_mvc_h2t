<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
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
<body class="flex flex-col min-h-screen">
<jsp:include page="../layout/header.jsp" />
<main class="flex-grow">
    <!-- Banner -->
    <div class="w-full">
        <img src="/images/banner.png" alt="Banner"
             class="w-full h-auto object-cover" />
    </div>
    <jsp:include page="../layout/products.jsp" />
</main>

<jsp:include page="../layout/footer.jsp" />
</body>
</html>
