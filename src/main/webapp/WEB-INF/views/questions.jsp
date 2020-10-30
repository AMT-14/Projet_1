<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.application.question.QuestionsDTO"/>

<c:set var="pageTitle" value="Ask"/>

<%@include file="parts/header.jsp"%>

<div class="container">
    <div class="row h-100">
         <div class="col">
             <form id="newQuestion" method="POST" action="submitQuestion.do">
                 <div  class="form-group">
                     <label for="newQuestion" class="text-grey-50 font-weight-bold">Ask a question</label>
                     <small id="questionTip" class="form-text text-muted text-gray-50">Stay away from accents man, yeah really</small>
                     <textarea id="tfText" name="text" class="form-control bg-warning" form="newQuestion" rows="3"></textarea>
                 </div>
                 <button id="bSubmitQuestion" name="bSubmitQuestion" type="submit" class="btn btn-primary">Ask my Question</button>
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
                            <div class="col">
                                <form action="vote.do" method="post">
                                    <input type="hidden" name="questionID" value="${question.getId().asString()}">
                                    <input type="hidden" name="textType" value="${TextType.QUESTION}">
                                    <input type="hidden" name="objectVotedType" value="question">
                                    <input type="hidden" name="voteID" value="">
                                    <input type="hidden" name="value" value="UP">
                                    <button type="submit"><i class="arrowUP"></i></button>
                                </form>
                                <!-- <span>${question.totalVotes}</span> -->
                                <h5>0</h5>
                                <form action="vote.do" method="post">
                                    <input type="hidden" name="questionID" value="${question.getId().asString()}">
                                    <input type="hidden" name="textType" value="${TextType.QUESTION}">
                                    <input type="hidden" name="objectVotedType" value="question">
                                    <input type="hidden" name="voteID" value="">
                                    <input type="hidden" name="value" value="DOWN">
                                    <button type="submit"><i class="arrowDOWN"></i></button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                </thead>
            </table>
     </div>
</div>
<jsp:include page="parts/footer.jsp"/>




