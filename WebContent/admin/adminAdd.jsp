<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String table = (String) request.getAttribute("table");
	String message = (String) request.getAttribute("message");
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add An Admin User</title>
</head>
<body>
<h1>Add An Admin User</h1>

<%= message %>
<br /><br />
<%= table %>

</body>
</html>