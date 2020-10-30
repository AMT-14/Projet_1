<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Login"/>
<%@include file="parts/header.jsp"%>
    <div class="container-fluid ">
        <div class="row">
            <div class="col">

             <form id="login" method="POST" action="login.do">
                 <div class="col-form-label">
                     <h3>Log In</h3>
                 </div>
                 <div class="form-group">
                     <input type="text" name="loginUsername" class="form-control" id="loginUsername" placeholder="Username">
                 </div>
                 <div class="form-group">
                     <input type="password" name="loginPassword" class="form-control" id="loginPassword" placeHolder="Password">
                 </div>
                 <button type="submit" id="bLogin" name="bLogin" class="btn btn-primary">Log In</button>
             </form>

            </div>

            <div class="col">
        <form id="register" method="POST" action="register.do">
            <div class="col-form-label">
                <h3>Register</h3>
            </div>
            <div class="form-group">
                <input id="registerUsername" name="registerUsername" class="form-control" placeholder="Username">
                <small id="usernameHelp" class="form-text text-muted">Username must be unique (not case sensitive)</small>
            </div>
            <div class="form-group">
                <input id="registerFirstName" name="registerFirstName" class="form-control" placeHolder="First name">
            </div>
            <div class="form-group">
                <input id="registerLastName" name="registerLastName" class="form-control" placeHolder="Last name">
            </div>
            <div class="form-group">
                <input id="registerEmail" name="registerEmail" class="form-control" placeHolder="Email">
            </div>
            <div class="form-group">
                <input id="registerPassword" type="password" class="form-control" name="registerPassword" placeholder="Password">
            </div>
            <button type="submit" id="bRegister" name="bRegister" class="btn btn-primary">Register</button>
        </form>
            </div>
        </div>
    </div>

    <div class="messages">
        <c:forEach var="errors" items="${errors}">
            <div class="error">${error}</div>
        </c:forEach>
    </div>

<jsp:include page="parts/footer.jsp"/>



