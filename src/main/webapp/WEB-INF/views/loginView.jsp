<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>AMT-14-Project1 Login </title>
    </head>

    <div>
        <body>
            <form id="login" method="POST" action="login.do">
                <input id="tfUsername" name="username" placeHolder="Username">
                <input id="tfPassword" type="password" name="password" placeHolder="Password">
                <button id="bLogin" type="submit">Log in</button>
             </form>
        </body>
    </div>
    <div>
        <div class="title">Register</div>

        <form id="register" method="POST" action="register.do">
            <input id="tfUsername" name="username" placeHolder="Username">
            <input id="tfFirstName" name="firstName" placeHolder="First name">
            <input id="tfLastName" name="lastName" placeHolder="Last name">
            <input id="tfEmail" name="email" placeHolder="Email">
            <input id="tfPassword" type="password" name="password" placeHolder="Password">
            <button id="bRegister" type="submit">Register</button>
         </form>

    </div>

    <div class="messages">
        <c:forEach var="errors" items="${errors}">
            <div class="error">${error}</div>
        </c:forEach>
    </div>

</html>