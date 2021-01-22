<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean scope="request" id="userBadges" type="java.lang.String"/>
<jsp:useBean scope="request" id="userScores" type="java.lang.String"/>

<c:set var="pageTitle" value="Profile"/>

<%@include file="parts/header.jsp"%>

<div>
    <h2>
        Badges
    </h2>
    <div class="col">
        <table class="table table-warning">
            <thead class="thead-dark">
                ${userBadges}
            </thead>
        </table>
    </div>
</div>
<div>
    <h2>
        score
    </h2>
    <div class="col">
        <table class="table table-warning">
            <thead class="thead-dark">
                ${userScores}
            </thead>
        </table>
    </div>
</div>

<jsp:include page="parts/footer.jsp"/>
