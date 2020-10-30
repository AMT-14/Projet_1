<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Project 1 - ${pageTitle}</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="icon" type="images/icon" href="images/favicon.ico"/>
        <link rel="arrowUP" type="image" href="images/arrowUP.png"/>
        <link rel="arrowDOWN" type="image" href="images/arrowDOWN.png"/>
    </head>
    <body>
        <div class="container-fluid bg-warning">
            <div class="row">
                <div class="col">
                    <h3 class="text-secondary">Projet 1 AMT<h3>
                </div>

                <div class="col">
                    <ul class="nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="questions">Ask</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Search</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Stats</a>
                        </li>
                    </ul>
                </div>
                <div class="col">
                    <div class="row">
                        <c:choose>
                            <c:when test="${true != false}">
                                <div class="row">
                                    <div class="text-secondary">
                                            ${currentUser.username}
                                    </div>
                                    <form id="logoutForm" method="POST" action="logout.do">
                                        <button type="submit" class="btn btn-warning text-secondary">Logout</button>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <h3 class="text-secondary">AMT-14</h3>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>