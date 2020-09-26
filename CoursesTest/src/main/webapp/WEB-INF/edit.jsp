<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Course</title>
</head>
<body>

	<form:form method="POST" action="/courses/${editCourse.id}/edit"
		modelAttribute="editCourse">
		<p>
			<form:label path="courseName">Course name: </form:label>
			<form:input type="text" path="courseName" />
		</p>
		<p>
			<form:label path="instructorName">Instructor's name: </form:label>
			<form:input type="text" path="instructorName" />
		</p>

		<p>
			<form:label path="courseCapacity">Max capacity: </form:label>
			<form:input type="number" path="courseCapacity" />
		</p>

		<input type="submit" value="Edit" />
	</form:form>



</body>
</html>