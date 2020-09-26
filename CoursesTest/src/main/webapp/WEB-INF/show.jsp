<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course detail</title>
</head>
<body>
	<h1>${course.courseName}</h1>
	<%-- 	<p>${creatorCourse.username}</p> --%>
	<p>Instructor: : ${course.instructorName}</p>
	<p>Sing ups: ${course.enroledUsers.size()} /
		${course.courseCapacity}</p>
	<c:if test="${currentUser.id == creatorCourse.id }">
		<a href="/courses/${course.id}/delete">Remove</a>
	</c:if>			
			
				
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Sign Up Date</th>
				<th>Actions:</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="enroled" value="${false}" />
			
			<c:forEach items="${enroledUsers}" var="user">
<%-- 			${course.creatorCourse.id } --%>
			<c:if test="${user.id == currentUser.id}">
				<c:set var="enroled" value="${true}" />
			</c:if>
				<tr>
					<td>${user.username}</td>
<%-- 					<td>${user}</td> --%>
					<td>
					<c:if test="${enroled==true && currentUser.id == user.id}">
<%-- 					<c:if test="${currentUser.id == user.id }"> --%>
						<a href="/courses/${course.id}/cancel">Cancel</a>
					</c:if>
<%-- 					</c:if> --%>
<%-- 					<c:otherwise> --%>
<%-- 						<a href="/courses/${course.id}/join">Add</a> --%>
<%-- 					</c:otherwise> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		


	<a href="/courses/${course.id}/edit">Edit</a>
	<a href="/courses/${course.id}/delete">Delete</a>
	<br/>
	<br/>
	<a href="/courses">Go back</a>

</body>
</html>