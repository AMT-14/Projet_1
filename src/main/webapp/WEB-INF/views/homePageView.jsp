<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<h2>Satistics</h2>
<ul>
    <c:forEach items="${HomePage.statistics}" var="value">
        <li>${value}</li>
    </c:forEach>
</ul>
<h2>Featured : </h2>
<h2>Post from ${HomePage.featured.author.username}</h2>
<ul>
    ${HomePage.featured.body}
</ul>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>
