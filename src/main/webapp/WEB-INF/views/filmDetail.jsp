<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- filmDetail.jsp -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Film Details</title>
</head>
<body>

	<h2>Film Details</h2>
	<div>
	 	<p><strong>ID:</strong> ${film.id}</p> <!-- Display film's ID -->
		<p>
			<strong>Title:</strong> ${film.title}
		</p>
		<p>
			<strong>Description:</strong> ${film.description}
		</p>
		<p>
			<strong>Release Year:</strong> ${film.releaseYear}
		</p>
		<p>
			<strong>Language:</strong> ${film.language}
		</p>
		<p>
			<strong>Rental Duration:</strong> ${film.rentalDuration} days
		</p>
		<p>
			<strong>Rental Rate:</strong> $${film.rentalRate}
		</p>
		<p>
			<strong>Length:</strong> ${film.length} minutes
		</p>
		<p>
			<strong>Replacement Cost:</strong> $${film.replacementCost}
		</p>
		<p>
			<strong>Rating:</strong> ${film.rating}
		</p>

		<p>
			<strong>Special Features:</strong>
			<c:forEach var="specialFeatures" items="${film.specialFeatures}"> 
                 ${specialFeatures}, 
             </c:forEach>
		</p>

		<p>
			<strong>Category:</strong>
			<c:forEach var="category" items="${film.categories}"> 
                 ${category}
             </c:forEach>
		</p>

		<p>
			<strong>Actors:</strong>
			<c:forEach var="actor" items="${film.actors}"> 
                 ${actor.firstName} ${actor.lastName}, 
             </c:forEach>
		</p>

	</div>
	
	<!-- Back to Home Page button -->
        <form action="home.do" method="GET">
            <input type="submit" value="Back to Home" />
        </form>

	<!-- added link to delete film from database    -->
	<form action="deleteFilm.do" method=POST>
		<input type="hidden" name="id" value="${film.id}" /> <input
			type="submit" value="Delete this Film" />
	</form>


</body>
</html>