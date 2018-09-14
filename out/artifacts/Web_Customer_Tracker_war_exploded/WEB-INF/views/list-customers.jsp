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
<h1>List Customers - coming soon....</h1>


<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Name</th>
            </tr>
            <c:forEach var="thempCustomer" items="${customers}">
                <tr>
                    <td>${thempCustomer.first_name}</td>
                    <td>${thempCustomer.last_name}</td>
                    <td>${thempCustomer.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<a href="/">Customer list!</a>
</body>
</html>
