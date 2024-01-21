<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

	<h3>Film By ID</h3>
	<form action="viewFilm.do">
		<input type="text" name="id" /> <input type="submit"
			value="Find Film" />
	</form>
	
<!--Mace Code  -->
	<br>

	<button onclick="window.location.href='/MVCFilmSite/filmForm.do'">Add New Film</button>

<!-- END -->
</body>


</html>


