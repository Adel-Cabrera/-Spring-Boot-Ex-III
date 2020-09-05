<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome</title>
<!-- 	<link rel="stylesheet" type="text/css" href="css/loginRegStyle.css"> -->
</head>
<body>
	<a href="/landing">Home</a>
	<form id="logoutForm" method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Logout"/>
	</form>
	
	<h2>Review of ${pool.address}</h2>
	<p><form:errors path="reviewObj.*"/></p>
	<form:form method="POST" action="/addReview" modelAttribute="reviewObj">
		<form:hidden path="guest.id" value="${currentUser.id}"/>
		<form:hidden path="pool.id" value="${pool.id}"/>
		<p>
			<form:label path="review">Review:</form:label>
			<form:input path="review"/>
		</p>
		<p>
			<form:label path="rating">Rating:</form:label>
			<form:input type="number" min="1" max="5" path="rating"/>
		</p>
		<input type="submit" value="Submit Review"/>
	</form:form>
</body>
</html>