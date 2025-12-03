<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Sửa Sản phẩm</title>
</head>
<body>
<h1>Sửa Sản phẩm</h1>

<form action="${pageContext.request.contextPath}/product/update" method="post">
    <input type="hidden" name="id" value="${product.id}">

    <p>
        Tên Sản phẩm:
        <input type="text" name="productName" value="${product.productName}" required>
    </p>

    <p>
        Số lượng:
        <input type="number" name="stockQuantity" value="${product.stockQuantity}" required>
    </p>

    <p>
        <input type="checkbox" name="isAvailable"
               <c:if test="${product.isAvailable}">checked</c:if>> Có sẵn
    </p>

    <p>
        Ngày nhập:
        <%
            String dateValue = "";
            if (request.getAttribute("product") != null) {
                model.Product p = (model.Product) request.getAttribute("product");
                if (p.getLastRestockDate() != null) {
                    dateValue = p.getLastRestockDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                }
            }
        %>
        <input type="datetime-local" name="lastRestockDate" value="<%= dateValue %>">
    </p>

    <button type="submit">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/product">Quay lại</a>
</form>
</body>
</html>
