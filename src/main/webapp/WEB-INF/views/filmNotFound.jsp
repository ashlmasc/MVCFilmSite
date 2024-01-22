<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- filmNotFound.jsp -->

<!DOCTYPE html>
<html>
<head>
    <title>Film Not Found</title>
</head>
<body>
    <h2>Film Not Found</h2>
    <p>${errorMessage}</p><br>
    
    <!-- Back to Home Page button -->
        <form action="." method="GET">
            <input type="submit" value="Back to Home" />
        </form>
    
</body>
</html>