<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h2>Error</h2>
    <p>${errorMessage}</p>
    <form action="." method="GET">
		<input type="submit" value="Back to Home" />
	</form>
</body>
</html>