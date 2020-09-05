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
	<h3>Email: ${pool.host.email}</h3>
	<h3>Name: ${pool.host.first_name} ${pool.host.last_name}</h3>
	
	<h2>${pool.address}</h2>
	<p>${pool.description}</p>
	<p>Pool Size: ${pool.size}</p>
	<p>Cost: $${pool.cost}</p>
	
	<h3>Reviews (${pool.average_reviews}/5)</h3>
	<c:choose>
		<c:when test="${principal == null}">
			<a href="/guest/signin">Log in to leave a review</a>
		</c:when>
		<c:otherwise>
			<c:if test="${currentUser.role == 'guest'}">
				<a href="/pools/${pool.id}/review">Leave a review</a>
			</c:if>
		</c:otherwise>
	</c:choose>
	<c:forEach var="row" items="${reviews}" varStatus="loop">
		<p>${row[0]} ${row[1]}</p>
		<p>${row[3]}/5</p>
		<p>${row[2]}</p>
		<c:if test="${!loop.last}">__________________________</c:if>
	</c:forEach>
</body>
</html>