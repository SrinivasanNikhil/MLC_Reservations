<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Banned User</title>
</head>
<body>
<h1>${user.userFirstName} 
	${user.userLastName} 
	has been banned from making reservations</h1>
<br />
<p>You have been banned due to not checking into a reserved room for two or more times this period.</p>
<br />
<p>For more information, contact XXXXXXX or XXXXXX<p>
 <br />

<form name="Logout" action="LoginController" method="get">
<input type="submit" name = "logout" value="Logout">
</form>

</body>
</html>