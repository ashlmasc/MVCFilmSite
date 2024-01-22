%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Film Details</title>
</head>
<body>
	<!--     <h2>Film Details</h2> -->
	<%--     <p>Title: ${film.title}</p> --%>
	<%--     <p>Description: ${film.description}</p> --%>
	<!-- Display other film details -->

	<h2>Film Details</h2>
	<c:forEach var="film" items="${films}">
		<c:if test="${not empty film}">
			<div>
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
				<form action="deleteFilm.do" method=POST>
					<input type="hidden" name="id" value="${film.id}" /> <input
						type="submit" value="Delete this Film" />
				</form>
				<br>
				<form action="updateFilm.do" method="GET">
					<input type="hidden" name="id" value="${film.id}" /> <input
						type="submit" value="Update Film" />
				</form>
			</div>
		</c:if>
	</c:forEach>

</body>


</html>