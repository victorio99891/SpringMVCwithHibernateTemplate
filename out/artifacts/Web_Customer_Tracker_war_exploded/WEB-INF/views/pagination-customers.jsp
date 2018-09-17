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
        <c:url var="showFormAdd_link" value="/customer/showFormAdd"/>
        <a href="${showFormAdd_link}">
            <button class="add-button">Add customer</button>
        </a>
        <form:select path="showOnPage" cssClass="showOnPage" onchange="location = this.value">
            <form:option value="" label="Results number"/>
            <c:url var="show5_link" value="/customer/pagination">
                <c:param name="pageNumber" value="1"/>
                <c:param name="showOnPage" value="5"/>
                <c:param name="orderBy" value="${orderBy}"/>
                <c:param name="direction" value="${direction}"/>
            </c:url>
            <form:option value="${show5_link}" label="5"/>
            <c:url var="show10_link" value="/customer/pagination">
                <c:param name="pageNumber" value="1"/>
                <c:param name="showOnPage" value="10"/>
                <c:param name="orderBy" value="${orderBy}"/>
                <c:param name="direction" value="${direction}"/>
            </c:url>
            <form:option value="${show10_link}" label="10"/>
            <c:url var="show15_link" value="/customer/pagination">
                <c:param name="pageNumber" value="1"/>
                <c:param name="showOnPage" value="15"/>
                <c:param name="orderBy" value="${orderBy}"/>
                <c:param name="direction" value="${direction}"/>
            </c:url>
            <form:option value="${show15_link}" label="15"/>
        </form:select><form:form action="searchCustomer" modelAttribute="searchBar" method="POST">
        Search for:
        <form:select path="searchBy" cssClass="showOnPage">
            <form:option label="First name" value="first_name"/>
            <form:option label="Last name" value="last_name"/>
            <form:option label="Email" value="email"/>
        </form:select>
        <form:input path="searchWord" cssClass="searchClass" placeholder="Type for search..." type="text"/>
        <button type="submit" class="add-button">Search</button>
    </form:form>
        <span style="font-size: 15px">[found results:&nbsp;</span><span
            style="color: red; font-size: 15px">${customersNumber}</span><span style="font-size: 15px">]</span>
        <table>
            <tr id="headTableRow">
                <th>First Name
                    <c:url var="firstNameASC" value="/customer/pagination">
                        <c:param name="pageNumber" value="${currentPage}"/>
                        <c:param name="showOnPage" value="${showOnPage}"/>
                        <c:param name="orderBy" value="first_name"/>
                        <c:param name="direction" value="asc"/>
                    </c:url>
                    <c:url var="firstNameDESC" value="/customer/pagination">
                        <c:param name="pageNumber" value="${currentPage}"/>
                        <c:param name="showOnPage" value="${showOnPage}"/>
                        <c:param name="orderBy" value="first_name"/>
                        <c:param name="direction" value="desc"/>
                    </c:url>
                    <a href="${firstNameASC}"><i id="firstNameArrowUp" class="arrow up"></i></a>
                    <a href="${firstNameDESC}"><i id="firstNameArrowDown" class="arrow down"></i></a></th>
                <th>Last Name
                    <c:url var="lastNameASC" value="/customer/pagination">
                        <c:param name="pageNumber" value="${currentPage}"/>
                        <c:param name="showOnPage" value="${showOnPage}"/>
                        <c:param name="orderBy" value="last_name"/>
                        <c:param name="direction" value="asc"/>
                    </c:url>
                    <c:url var="lastNameDESC" value="/customer/pagination">
                        <c:param name="pageNumber" value="${currentPage}"/>
                        <c:param name="showOnPage" value="${showOnPage}"/>
                        <c:param name="orderBy" value="last_name"/>
                        <c:param name="direction" value="desc"/>
                    </c:url>
                    <a href="${lastNameASC}"><i id="lastNameArrowUp" class="arrow up"></i></a>
                    <a href="${lastNameDESC}"><i id="lastNameArrowDown" class="arrow down"></i></a></th>
                <th>Email
                    <c:url var="emailASC" value="/customer/pagination">
                        <c:param name="pageNumber" value="${currentPage}"/>
                        <c:param name="showOnPage" value="${showOnPage}"/>
                        <c:param name="orderBy" value="email"/>
                        <c:param name="direction" value="asc"/>
                    </c:url>
                    <c:url var="emailDESC" value="/customer/pagination">
                        <c:param name="pageNumber" value="${currentPage}"/>
                        <c:param name="showOnPage" value="${showOnPage}"/>
                        <c:param name="orderBy" value="email"/>
                        <c:param name="direction" value="desc"/>
                    </c:url>
                    <a href="${emailASC}"><i id="emailArrowUp" class="arrow up"></i></a>
                    <a href="${emailDESC}"><i id="emailArrowDown" class="arrow down"></i></a></th>
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
        <div id="numberPages">
            <c:if test="${currentPage != 1}">
                <c:url var="lastPage_link" value="/customer/pagination">
                    <c:param name="pageNumber" value="${currentPage-1}"/>
                    <c:param name="showOnPage" value="${showOnPage}"/>
                    <c:param name="orderBy" value="${orderBy}"/>
                    <c:param name="direction" value="${direction}"/>
                </c:url>
                <a class="pageNumbers"
                   href="${lastPage_link}">[<]</a>
            </c:if>
            <c:url var="currentPage_link" value="/customer/pagination">
                <c:param name="pageNumber" value="${currentPage}"/>
                <c:param name="showOnPage" value="${showOnPage}"/>
                <c:param name="orderBy" value="${orderBy}"/>
                <c:param name="direction" value="${direction}"/>
            </c:url>
            <a class="pageNumbers"
               href="${currentPage_link}">[${currentPage}]</a>
            <c:if test="${currentPage != lastPage}">
                <c:url var="nextPage_link" value="/customer/pagination">
                    <c:param name="pageNumber" value="${currentPage+1}"/>
                    <c:param name="showOnPage" value="${showOnPage}"/>
                    <c:param name="orderBy" value="${orderBy}"/>
                    <c:param name="direction" value="${direction}"/>
                </c:url>
                <a class="pageNumbers"
                   href="${nextPage_link}">[>]</a>
            </c:if>
        </div>
    </div>
</div>

<br/><br/>
<c:url var="goHome_link" value="/"/>
<a href="${goHome_link}">
    <button class="add-button">Go home!</button>
</a>
<script src="${pageContext.request.contextPath}/resources/script/colorSortingArrows.js"></script>
</body>
</html>
