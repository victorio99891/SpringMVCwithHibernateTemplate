<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRM - Customer Relation Manager</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/style.css">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/style/add-customer-style.css">
    <script src="${pageContext.request.contextPath}/resources/script/test.js"></script>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <h3>Save Customer</h3>

    <form:form action="saveCustomer" modelAttribute="customer" method="POST">

        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><form:input path="first_name"/></td>
            </tr>

            <tr>
                <td><label>Last name:</label></td>
                <td><form:input path="last_name"/></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><form:input path="email"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>


            </tbody>
        </table>


    </form:form>

    <div style="clear: both"></div>

    <p>
        <a href="/customer/list">
            <button class="add-button">Back to list!</button>
        </a>
    </p>

</div>
</body>
</html>
