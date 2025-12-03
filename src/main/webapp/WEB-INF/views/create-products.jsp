<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thêm Sản phẩm</title>
</head>
<body>
<h1>Thêm Sản phẩm mới</h1>

<form action="${pageContext.request.contextPath}/product/create" method="post">
    <p>
        Tên Sản phẩm:
        <input type="text" name="productName" required>
    </p>

    <p>
        Số lượng:
        <input type="number" name="stockQuantity" required>
    </p>

    <p>
        <input type="checkbox" name="isAvailable"> Có sẵn
    </p>

    <p>
        Ngày nhập:
        <input type="datetime-local" name="lastRestockDate">
    </p>

    <button type="submit">Thêm mới</button>
    <a href="${pageContext.request.contextPath}/product">Quay lại</a>
</form>
</body>
</html>
