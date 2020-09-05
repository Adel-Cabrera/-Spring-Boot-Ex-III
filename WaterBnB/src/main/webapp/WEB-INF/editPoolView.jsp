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
	<a href="/host/dashboard">Home</a>
	<form id="logoutForm" method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Logout"/>
	</form>
	
	<h3>Email: ${currentUser.email}</h3>
	<h3>Name: ${currentUser.first_name} ${currentUser.last_name}</h3>
	
	<h2>${row[1]}</h2>
	<p><form:errors path="pool.*"/></p>
	<form:form method="POST" action="/edit" modelAttribute="pool">
		<form:hidden path="id" value="${row[0]}"/>
		<p>
			<form:label path="description">Description:</form:label>
			<form:input path="description" value="${row[2]}"/>
		</p>
		<p>
			<form:label path="cost">Cost per night: $
			<form:input type="number" step="0.01" path="cost" value="${row[3]}"/></form:label>
		</p>
		<p>
			<form:label path="size">Pool Size</form:label>
			<form:select path="size">
				<option selected disabled>Size</option>
				<form:option value="Small">Small</form:option>
				<form:option value="Medium">Medium</form:option>
				<form:option value="Large">Large</form:option>
			</form:select>
		</p>
		<input type="submit" value="Save Changes"/>
	</form:form>
	
	<h3>Reviews (${pool.average_reviews}/5):</h3>
	<c:forEach var="row" items="${reviews}" varStatus="loop">
		<p>${row[0]} ${row[1]}</p>
		<p>${row[3]}/5</p>
		<p>${row[2]}</p>
		<c:if test="${!loop.last}">__________________________</c:if>
	</c:forEach>
</body>
</html>