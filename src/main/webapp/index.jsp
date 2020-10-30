<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Home"/>

 <%@include file="/WEB-INF/views/parts/header.jsp"%>

    <div class="h-100">
        <div class="container bg-info">
            <div class="row">
                <div class="col-12">
                    <h2>Welcome to Project 1 !</h2>
                </div>
            </div>
            <p>This page is just the landing page, use the navbar up top to go to actual pages ;)</p>
        </div>

    </div>


 <jsp:include page="WEB-INF/views/parts/footer.jsp"/>
