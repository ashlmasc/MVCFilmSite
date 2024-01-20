<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>New State</title>
</head>
<body>
	<form action="NewState.do" method="POST">
		<label for="abbreviation">Abbrev:</label> <input type="text"
			name="abbreviation" value="PR"> <br> <label for="name">Name:</label>
		<input type="text" name="name" value="Puerto Rico"> <br>
		<label for="capital">Capital:</label> <input type="text"
			name="capital" value="San Juan"> <br> <input
			type="submit" value="Add State">
	</form>
</body>
</html>