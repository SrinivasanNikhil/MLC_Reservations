<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String table = (String) request.getAttribute("table");
	String message = (String) request.getAttribute("message");
%> 
    
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="WEB-INF/global.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>View Reservations Home Page</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script> 
<!-- <<<<<<< HEAD-->
	<script> 

	$(function() {
		$("#header").load("./userheader.html"); 
		$("#footer").load("../footer.html"); 
	});
	</script> 
	</head>
	<body>
	<div id="header"></div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

		<script> 
			$(function() {
				$("#header").load("header.html"); 
				$("#footer").load("footer.html"); 
			});
		</script> 
	</head>
	<body>
		<div id="header"></div>
<!-- >>>>>>>> origin/master-->
		<h1>View Home Page</h1>
		<h2>Reservations for: 
			${user.userFirstName} 
			${user.userLastName}
		</h2>
			<%= message %>
			<%= table %>
		<div id="footer"></div>
	</body>
</html>