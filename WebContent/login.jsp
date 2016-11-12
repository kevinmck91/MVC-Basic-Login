<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<form action="/MVC_Basic_Login/MVCController" method="post" >
<input type="hidden" name="action" value="dologin" />
Email: <input type="text" name="email" value="<%= request.getAttribute("email") %>" />
Password: <input type="text" name="password" value="<%= request.getAttribute("password") %>" />
<input type="submit"value="OK" />
</form>

<h2><%= request.getAttribute("validationmessage") %></h2>

</body>
</html>