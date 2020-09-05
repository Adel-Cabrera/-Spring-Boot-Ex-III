<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WaterBnB</title>
<!-- 	<link rel="stylesheet" type="text/css" href="css/loginRegStyle.css"> -->
</head>
<body>
	<c:choose>
		<c:when test="${principal == null}">
			<a href="/landing">Home</a>
			<a href="/guest/signin">Log In</a>
		</c:when>
		<c:otherwise>
			<c:if test="${currentUser.role == 'host'}">
				<a href="/host/dashboard">Home</a>
			</c:if>
			<c:if test="${currentUser.role == 'guest'}">
				<a href="/landing">Home</a>
			</c:if>
			<form id="logoutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="Logout"/>
			</form>
		</c:otherwise>
	</c:choose>
	
	<h2>Find Your Pool!</h2>
	<form method="POST" action="/search">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<label>Search:
		<input type="text" name="searchQuery"></label>
		<input type="submit" value="Search">
	</form>
	<table>
		<tr>
			<th>Address</th>
			<th>Pool Size</th>
			<th>Cost per night</th>
			<th>Details</th>
		</tr>
		<c:forEach var="pool" items="${pools}">
			<tr>
				<td>${pool.address}</td>
				<td>${pool.size}</td>
				<td>${pool.cost}</td>
				<td><a href="/pools/${pool.id}">${pool.average_reviews}/5 - See More</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>