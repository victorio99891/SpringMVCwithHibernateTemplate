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
        You searched for: <span style="color: #ff0100">"${searchBar.searchWord}"</span><span style="font-size: 15px">[field: ${searchBar.searchBy}, found results:&nbsp;</span><span
            style="color: red; font-size: 20px">${customers.size()}</span><span style="font-size: 15px">]</span>

        <table>
            <tr id="headTableRow">
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
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
                    <td class="searchFirstName">${tempCustomer.first_name}</td>
                    <td class="searchLastName">${tempCustomer.last_name}</td>
                    <td class="seachEmail">${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}"
                                                                onclick="if (!(confirm('Are you sure to delete this user?\n       Name: ${tempCustomer.first_name}\nLast name: ${tempCustomer.last_name}\n        Email: ${tempCustomer.email}'))) return false;">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<br/><br/>
<c:url var="goToAllCustomers" value="/customer/pagination">
    <c:param name="pageNumber" value="1"/>
    <c:param name="showOnPage" value="5"/>
    <c:param name="orderBy" value="last_name"/>
    <c:param name="direction" value="asc"/>
</c:url>
<a href="${goToAllCustomers}">
    <button class="add-button">Go to all customers!</button>
</a>
<script src="${pageContext.request.contextPath}/resources/script/colorSortingArrows.js"></script>
<script src="${pageContext.request.contextPath}/resources/script/search-color.js"></script>
</body>
</html>
