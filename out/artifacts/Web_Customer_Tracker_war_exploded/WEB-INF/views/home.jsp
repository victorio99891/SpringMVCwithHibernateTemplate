<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/home.css">
    <script src="${pageContext.request.contextPath}/resources/script/test.js"></script>
</head>
<body>
<h1>Hello world!</h1>

<c:url var="normalList" value="/customer/list"/>
<a href="${normalList}">Customer list!</a>

<c:url var="paginatedList" value="/customer/pagination">
    <c:param name="pageNumber" value="1"/>
    <c:param name="showOnPage" value="5"/>
    <c:param name="orderBy" value="last_name"/>
    <c:param name="direction" value="asc"/>
</c:url>
<a href="${paginatedList}">Paginated list!</a>

</body>
</html>
