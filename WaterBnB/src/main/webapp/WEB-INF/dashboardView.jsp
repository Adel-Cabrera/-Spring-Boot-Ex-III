<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WaterBnB</title>
</head>
<body>
	<a href="/landing">Home</a>
	<form id="logoutForm" method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Logout"/>
	</form>
	
	<h2>Current Listings</h2>
	<table>
		<tr>
			<th>Address</th>
			<th>Pool Size</th>
			<th>Cost per night</th>
			<th>Details</th>
		</tr>
		<c:forEach var="row" items="${userListings}">
			<tr>
				<td>${row[1]}</td>
				<td>${row[2]}</td>
				<td>$${row[3]}</td>
				<td><a href="/host/pools/${row[0]}">${row[4]}/5 - Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<h2>New Listing</h2>
	<p><form:errors path="pool.*"/></p>
	<form:form method="POST" action="/createPool" modelAttribute="pool">
		<p>
			<form:label path="address">Address</form:label>
			<form:input path="address"/>
		</p>
		<p>
			<form:label path="description">Description:</form:label>
			<form:input path="description"/>
		</p>
		<p>
			<form:label path="cost">Cost per night: $
			<form:input type="number" step="0.01" path="cost"/></form:label>
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
		<input type="submit" value="Add Listing"/>
	</form:form>
</body>
</html>