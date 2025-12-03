<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách Sản phẩm</title>
</head>
<body>
<h1>Danh sách Sản phẩm</h1>

<a href="${pageContext.request.contextPath}/product/create">Thêm mới</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên Sản phẩm</th>
        <th>Số lượng</th>
        <th>Có sẵn</th>
        <th>Ngày nhập</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.stockQuantity}</td>
            <td>${p.isAvailable ? "Có" : "Không"}</td>
            <td>${p.lastRestockDate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/product/update?id=${p.id}">Sửa</a>
                <a href="${pageContext.request.contextPath}/product/delete?id=${p.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
