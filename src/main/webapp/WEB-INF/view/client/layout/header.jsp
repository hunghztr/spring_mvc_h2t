<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<header class="bg-white shadow-lg fixed top-0 left-0 right-0 z-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center py-4">

            <div class="text-xl font-bold text-primary">
                <a href="/" class="hover:text-primary">H2T</a>
            </div>

            <!-- Navigation Menu -->
            <nav class="hidden md:flex space-x-8 text-gray-700 font-medium">

                <!-- Dropdown Sản phẩm với hiệu ứng mượt và giữ hover -->
                <div class="relative group">
                    <button class="hover:text-[#e85205] transition-colors duration-300 focus:outline-none">
                        Sản phẩm
                    </button>

                    <!-- Dropdown: hiệu ứng mượt -->
                    <div class="absolute left-0 mt-2 w-48 bg-white border shadow-lg rounded z-50
                opacity-0 invisible group-hover:visible group-hover:opacity-100
                transition-all duration-500 ease-out transform translate-y-2 group-hover:translate-y-3">
                        <div class="py-1">
                            <c:forEach var="cate" items="${categories}" begin="1">
                                <a href="/filter-cate?cid=${cate.id}"
                                   class="block px-4 py-2 text-sm text-gray-700
              hover:text-[#e85205]
              hover:ring-1 hover:ring-[#e85205]
              transition-all duration-300 rounded-md">
                                    <c:out value="${cate.name}"/>
                                </a>
                            </c:forEach>

                        </div>
                    </div>
                </div>


                <a href="/sale" class="hover:text-primary">Sản phẩm sale</a>
                <a href="/introduce" class="hover:text-primary">Địa chỉ</a>
                <a href="/policy" class="hover:text-primary">Chính sách</a>
            </nav>

            <!-- Right Section: Search, Cart, Avatar -->
            <div class="flex items-center space-x-4">
                <!-- Search -->
                <div class="relative">
                    <form action="/" method="get">
                        <input
                                name="keyword"
                                type="text"
                                placeholder="Tìm kiếm..."
                                class="pl-3 pr-10 py-2 border rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary"
                        />
                        <svg
                                class="w-5 h-5 absolute right-2 top-2.5 text-gray-400"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                        >
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M21 21l-4.35-4.35M11 18a7 7 0 1 1 0-14 7 7 0 0 1 0 14z"/>
                        </svg>
                    </form>
                </div>

                <!-- Cart Icon -->
                <a href="#" class="relative text-gray-700 hover:text-primary">
                    <svg
                            class="w-6 h-6"
                            fill="none"
                            stroke="currentColor"
                            stroke-width="2"
                            viewBox="0 0 24 24"
                    >
                        <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13l-1.5 7h11l-1.5-7M7 13h10"
                        />
                    </svg>
                    <span class="absolute -top-2 -right-2 bg-red-500 text-white text-xs rounded-full px-1">${sessionScope.sum}</span>
                </a>

                <!-- Avatar -->
                <div class="relative inline-block text-left">
                    <c:choose>
                        <c:when test="${not empty sessionScope.username}">
                            <button id="userMenuButton"
                                    class="text-sm font-medium text-gray-700 px-4 py-2 rounded-md border border-gray-300 hover:border-[#e85205]">
                                Xin chào, ${sessionScope.username}
                            </button>

                            <!-- Dropdown menu -->
                            <div id="userDropdown"
                                 class="hidden absolute right-0 mt-2 w-48 bg-white border border-gray-200 rounded-md shadow-lg z-50">
                                <form action="/logout" method="post" class="block">
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <button type="submit"
                                            class="w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-gray-100">
                                        Đăng xuất
                                    </button>
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="/login"
                               class="text-sm font-medium text-gray-700 hover:text-[#e85205] px-4 py-2 rounded-md border border-gray-300 hover:border-[#e85205] transition">
                                Đăng nhập
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
        </div>
    </div>
</header>
<script>
    const userBtn = document.getElementById("userMenuButton");
    const dropdown = document.getElementById("userDropdown");

    if (userBtn) {
        userBtn.addEventListener("click", function (event) {
            event.stopPropagation(); // ngăn click lan ra ngoài
            dropdown.classList.toggle("hidden");
        });

        // Ẩn dropdown khi click ra ngoài
        document.addEventListener("click", function (event) {
            if (!dropdown.contains(event.target) && !userBtn.contains(event.target)) {
                dropdown.classList.add("hidden");
            }
        });
    }
</script>
