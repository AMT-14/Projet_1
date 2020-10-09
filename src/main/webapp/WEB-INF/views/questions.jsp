
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.application.question.QuestionsDTO"/>
<html>
<head>
    <title>Ask a Question</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
<jsp:include page="parts/header.jsp"/>

<div class="container-fluid bg-danger">
    <div class="row">
         <div class="col">
             <form id="newQuestion" method="POST" action="submitQuestion.do">
                 <div  class="form-group">
                     <label for="newQuestion" class="text-white-50 font-weight-bold">Ask a question</label>
                     <small id="questionTip" class="form-text text-muted text-white-50">Stay away from accents man, yeah really</small>
                     <textarea id="tfText" name="text" class="form-control bg-warning" form="newQuestion" rows="3"></textarea>
                 </div>
                 <button id="bSubmitQuestion" type="submit" class="btn btn-primary">Ask my Question</button>
             </form>
        </div>
        <div class="col">
            <table class="table table-warning">
                <thead class="thead-dark">
                <tr>
                    <th>Questions</th>
                </tr>
                <c:forEach var="question" items="${questions.questions}">
                    <tr>
                        <td>
                            <div class="question text-black-50">${question.text}</div>
                        </td>
                    </tr>
                </c:forEach>

                </thead>
            </table>
     </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>



