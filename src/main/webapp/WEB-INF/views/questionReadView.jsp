
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Question</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>
<div class="jumbotron jumbotron-fluid">
    <div class="container bg-secondary">
        <h1 class="display-4">Question from ${question.author.username}</h1>
        <p class="lead">${question.body}</p>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>