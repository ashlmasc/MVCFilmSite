<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add New Film</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f4;
        }
    </style>
    <script>
    function addActorField() {
        var actorsContainer = document.getElementById("actors-container");
        var newActorField = document.createElement("input");
        newActorField.type = "text";
        newActorField.name = "actors";
        newActorField.className = "form-control";
        newActorField.placeholder = "Enter actor's name";
        actorsContainer.appendChild(newActorField);
    }
</script>
</head>
<body>
    <div class="container mt-5">
        <form action="addFilm.do" method="POST">
            <h2 class="text-center mb-4">Add New Film</h2>

            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="form-control" required></textarea>
            </div>

            <div class="form-group">
                <label for="releaseYear">Release Year:</label>
                <input type="text" id="releaseYear" name="releaseYear" class="form-control" required>
            </div>

            <!-- <div class="form-group">
                <label for="language">Language:</label>
                <input type="text" id="language" name="language" class="form-control" required>
            </div> -->

            <!-- <div class="form-group">
                <label for="rentalDuration">Rental Duration (days):</label>
                <input type="text" id="rentalDuration" name="rentalDuration" class="form-control" required>
            </div> -->

           <!--  <div class="form-group">
                <label for="rentalRate">Rental Rate:</label>
                <input type="text" id="rentalRate" name="rentalRate" class="form-control" required>
            </div> -->

            <div class="form-group">
                <label for="length">Length (minutes):</label>
                <input type="text" id="length" name="length" class="form-control" required>
            </div>

            <!-- <div class="form-group">
                <label for="replacementCost">Replacement Cost:</label>
                <input type="text" id="replacementCost" name="replacementCost" class="form-control" required>
            </div> -->

            <div class="form-group">
                <label for="rating">Rating:</label>
                <input type="text" id="rating" name="rating" class="form-control" required>
            </div>

            <!-- <div class="form-group">
                <label for="specialFeatures">Special Features:</label>
                <input type="text" id="specialFeatures" name="specialFeatures" class="form-control" required>
            </div> -->

           <!--  <div class="form-group">
                <label for="category">Category:</label>
                <input type="text" id="category" name="category" class="form-control" required>
            </div> -->

            <div class="form-group">
				<label for="actors">Actors:</label>
					<div id="actors-container">
						<input type="text" name="actors" class="form-control" placeholder="Enter actor's name" required>
					</div>
				<button type="button" onclick="addActorField()" class="btn btn-secondary">Add Actor</button>
			</div>

            <button type="submit" class="btn btn-success btn-block">Add Film</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>