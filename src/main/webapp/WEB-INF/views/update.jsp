<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Update Film</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f4;
        }
    </style>
    <script>
</script>
</head>
<body>
    <div class="container mt-5">
        <form action="filmDetail.do?id=${film.id}" method="POST">
            <h2 class="text-center mb-4">Update Film</h2>

            <div class="form-group">
                <label for="title">Title:</label>
                <input value="${film.title}" type="text" id="title" name="title" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <input value="${film.description}" type="text" size="200" id="description" name="description" class="form-control" required></input>
            </div>

            <div class="form-group">
                <label for="releaseYear">Release Year:</label>
                <input value="${film.releaseYear}" type="text" id="releaseYear" name="releaseYear" class="form-control" required>
            </div>
            
           <div class="form-group">
                <label for="language">Language:</label>
                <input value="${film.language}" type="text" id="language" name="language" class="form-control">
            </div> 

             <div class="form-group">
                <label for="rentalDuration">Rental Duration (days):</label>
                <input value="${film.rentalDuration}" type="text" id="rentalDuration" name="rentalDuration" class="form-control">
            </div>

             <div class="form-group">
                <label for="rentalRate">Rental Rate:</label>
                <input value="${film.rentalRate}" type="text" id="rentalRate" name="rentalRate" class="form-control">
            </div> 


            <div class="form-group">
                <label for="length">Length (minutes):</label>
                <input value="${film.length}" type="text" id="length" name="length" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label for="replacementCost">Replacement Cost:</label>
                <input value="${film.replacementCost}" type="text" id="replacementCost" name="replacementCost" class="form-control">
            </div> 


            <div class="form-group">
                <label for="rating">Rating:</label>
                <input value="${film.rating}" type="text" id="rating" name="rating" class="form-control" required>
            </div>


			<button type="submit" class="btn btn-success btn-block">Update Film</button>
			<br>
        	<!-- Button to cancel update and return to the previous page -->
            <button type="button" onclick="history.back();" class="btn btn-secondary">Cancel and Return to Previous Page</button>

            <!-- Link to return to the home page -->
            <a href="." class="btn btn-primary">Return to Home Page</a>
            
            <br>
            <br>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>