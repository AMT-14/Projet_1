<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.application.question.QuestionsDTO"/>

<c:set var="pageTitle" value="Browse"/>

<%@include file="parts/header.jsp"%>

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
                            <input type="hidden" name="questionID" value="${question.id}">
                            <input type="hidden" name="textType" value="${TextType.QUESTION}">
                            <input type="hidden" name="objectVotedType" value="question">
                            <input type="hidden" name="voteID" value="">
                            <input type="hidden" name="value" value="UP">
                            <button type="submit">arrow up</button>
                        </form>

                        <span>${question.getTotalVotes()}</span>
                        <form action="vote.do" method="post">
                            <input type="hidden" name="questionID" value="${question.id}">
                            <input type="hidden" name="textType" value="${TextType.QUESTION}">
                            <input type="hidden" name="objectVotedType" value="question">
                            <input type="hidden" name="voteID" value="">
                            <input type="hidden" name="value" value="DOWN">
                            <button type="submit">arrow down</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>

        </thead>
    </table>
</div>

<jsp:include page="parts/footer.jsp"/>
