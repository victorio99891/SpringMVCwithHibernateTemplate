<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRM - Customer Relation Manager</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/arrows.css">
    <script src="${pageContext.request.contextPath}/resources/script/test.js"></script>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <a href="/customer/showFormAdd">
            <button class="add-button">Add customer</button>
        </a>
        <table>
            <tr>
                <th>First Name <a href="/customer/list/first_name/asc"><i class="arrow up"></i></a> <a
                        href="/customer/list/first_name/desc"><i class="arrow down"></i></a></th>
                <th>Last Name <a href="/customer/list/last_name/asc"><i class="arrow up"></i></a> <a
                        href="/customer/list/last_name/desc"><i class="arrow down"></i></a></th>
                <th>Email Name <a href="/customer/list/email/asc"><i class="arrow up"></i></a> <a
                        href="/customer/list/email/desc"><i class="arrow down"></i></a></th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempCustomer" items="${customers}">

                <c:url var="updateLink" value="/customer/showFormUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <c:url var="deleteLink" value="/customer/deleteCustomer">
                    <c:param name="customerId" value="${tempCustomer.id}"/>
                </c:url>

                <tr>
                    <td>${tempCustomer.first_name}</td>
                    <td>${tempCustomer.last_name}</td>
                    <td>${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}"
                                                              onclick="if (!(confirm('Are you sure to delete this user?\n       Name: ${tempCustomer.first_name}\nLast name: ${tempCustomer.last_name}\n        Email: ${tempCustomer.email}'))) return false;">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<br/><br/>
<a href="/">
    <button class="add-button">Go home!</button>
</a>
</body>
</html>
