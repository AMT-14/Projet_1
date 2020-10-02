
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.application.question.QuestionsDTO"/>
<html>
<head>
    <title>Temporary</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>

<div class="columns">
    <div class="authoring">
        <div class="title">Ask a question</div>
        <form id="newQuestion" method="POST" action="submitQuestion.do">
            <textarea id="tfText" name="text" form="newQuestion" rows="4"></textarea>
            <button id="bSubmitQuestion" type="submit">Ask my Question</button>
        </form>
    </div>
    <div class="questions">
        <div class="title">Questions</div>
        <c:forEach var="question" items="${questions.questions}">
            <div class="question">${question.text}</div>
        </c:forEach>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>