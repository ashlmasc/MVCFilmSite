<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Film Details</title>
</head>
<body>

	<h2>Film Details</h2>
	<div>
	 	<strong>Film ID:</strong> ${film.id}<br>
			<strong>Title:</strong> ${film.title}<br>
			<strong>Description:</strong> ${film.description}<br>
			<strong>Release Year:</strong> ${film.releaseYear}<br>
			<strong>Language:</strong> ${film.language}<br>
			<strong>Rental Duration:</strong> ${film.rentalDuration} days<br>
			<strong>Rental Rate:</strong> $${film.rentalRate}<br>
			<strong>Length:</strong> ${film.length} minutes<br>
			<strong>Replacement Cost:</strong> $${film.replacementCost}<br>
			<strong>Rating:</strong> ${film.rating}<br>
			<strong>Special Features:</strong>
			<c:forEach var="specialFeatures" items="${film.specialFeatures}" varStatus="status"> 
                 ${specialFeatures}${not status.last ? ', ' : ''}
             </c:forEach>
			<br>
			<strong>Category:</strong>
			<c:forEach var="category" items="${film.categories}" varStatus="status"> 
                 ${category}${not status.last ? ', ' : ''}
             </c:forEach>
             <br>
			<strong>Actors:</strong>
			<c:forEach var="actor" items="${film.actors}" varStatus="status"> 
                 ${actor.firstName} ${actor.lastName}${not status.last ? ', ' : ''}
             </c:forEach>
	</div>
	
	<!-- Back to Home Page button -->
        <form action="." method="GET">
            <input type="submit" value="Back to Home" />
        </form>
        
        <br><br>

	<!-- added link to delete film from database    -->
	<form action="deleteFilm.do" method=POST>
		<input type="hidden" name="id" value="${film.id}" /> <input
			type="submit" value="Delete this Film" />
	</form>
	<br><br>
	
	<form action="updateFilm.do?id=${film.id}" method=POST>
		<input type="submit" value="Update Film" />
	</form>
</body>
</html>