<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>

	<h1>
		Welcome
		<c:out value="${currentUser.username}"></c:out>
	</h1>
	<c:forEach items="${userRoles}" var="role">
		<%--     	<p>${role.id}</p> --%>
		<%--     	<p>${role.name}</p> --%>
		<c:if test="${role.id == 2}">
			<a href="/admin">Admin panel</a>
		</c:if>
	</c:forEach>

	<%--     <c:out value="${currentUser.email}"></c:out> --%>

	<form id="logoutForm" method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" value="Logout!" />
	</form>

	<br />
	<br />
	<h2>All courses</h2>
	<a href="/courses/asc">Order by asc</a>
	<a href="/courses/desc">Order by desc</a>

	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Instructor</th>
				<th>Capacity</th>
				<th>Actions:</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allCourses}" var="course">
				<%-- 			${course.creatorCourse.id } --%>
				<tr>
					<td><a href="/courses/${course.id}">${course.courseName}</a></td>
					<td>${course.instructorName}</td>
					<td>${course.enroledUsers.size()}/ ${course.courseCapacity}</td>
					<td>
									<c:set var="enroled" value="${false}" />
									<c:forEach items="${course.getEnroledUsers()}" var="enroledUser">
									<c:if test="${enroledUser == currentUser}">
										<c:set var="enroled" value="${true}" />
									</c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${enroled==false && course.enroledUsers.size() < course.courseCapacity}" >
											<a href="/courses/${course.id}/join">Add</a>							
										</c:when>	
									
										<c:when test="${enroled==false && course.enroledUsers.size() >= course.courseCapacity}" >
											<p>Full</p>	 							
										</c:when>
										<c:when test="${enroled==true && course.enroledUsers.size() >= course.courseCapacity}" >
											<p>Full | <a href="/courses/${course.id}/cancel">Cancel</a></p>	 							
										</c:when>								
																		
										<c:otherwise>
											<p>Already Added
<%-- 											  | <a href="/courses/${course.id}/cancel">Cancel</a> --%>
											 <p>
										</c:otherwise>					
									
									</c:choose>									
							
						</td>
						
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/courses/new">New course</a>


</body>
</html>