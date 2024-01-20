<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- filmDetail.jsp -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Film Details</title>
</head>
<body>
    <h2>Film Details</h2>
    <p>Title: ${film.title}</p>
    <p>Description: ${film.description}</p>
    <!-- Display other film details -->
</body>
</html>