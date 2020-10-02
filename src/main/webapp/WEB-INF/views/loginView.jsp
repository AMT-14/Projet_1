<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>AMT-14-Project1 Login </title>
    </head>

    <body>
        <form action="LoginCommandServlet" method="post">
                <table style="with: 60%">
                    <tr>
                        <td>email</td>
                        <td>
                            <input type="text" name="email"/>
                        </td>
                    </tr>
                        <tr>
                        <td>Password</td>
                        <td>
                            <input type="password" name="password"/>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="Login"/>
         </form>
    </body>

</html>