<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="userStats" type="ch.heig.gamification.api.dto.UserStat"/>

<c:set var="pageTitle" value="Profile"/>

<%@include file="parts/header.jsp"%>

<div>
    <h2>
        Badges
    </h2>
    <c:foreach var="userBadge" items="${userStats.badges}">
        ${userBadge.name}
    </c:foreach>
</div>
<div>
    <h2>
        score
    </h2>
    <c:foreach var="userScore" items="${userStats.scores}">
        ${userBadge.name}
    </c:foreach>
</div>

<jsp:include page="parts/footer.jsp"/>
