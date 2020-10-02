<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<h2>Welcome to the registration page</h2>
<h4>Enter your username and password</h4>
        <form action="RegisterCommandServlet" method="post">
			<table style="with: 60%">

				<tr>
					<td>email</td>
					<td><input type="text" name="email" /></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>

				</tr>
			</table>
			<input type="submit" value="Submit" />
		</form>

</body>
</body>
</html>