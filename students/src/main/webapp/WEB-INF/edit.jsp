<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Post</title>
</head>
<body>
	<form:form method="POST" action="/posts/edit/${editPost.id}" modelAttribute="editPost">
<!--     	<input type="hidden" name="_method" value="put"> -->
<%--     		<form:input type="hidden" path="id" value="${editPost.id}"/> --%>
		<p>
			<form:label path="postTitle">Title: </form:label>
			<form:input type="text" path="postTitle" />
		</p>
		<p>
			<form:label path="postTitle">Description: </form:label>
			<form:input type="text" path="postDescription" />
		</p>

		<input type="submit" value="Submit" />
	</form:form>


</body>
</html>