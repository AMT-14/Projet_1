<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.application.question.QuestionsDTO"/>

<c:set var="pageTitle" value="Details"/>

<%@include file="parts/header.jsp"%>

<jsp:include page="parts/footer.jsp"/>
