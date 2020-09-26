<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New post</title>
</head>
<body>
	<form:form method="POST" action="/posts/create" modelAttribute="newPost">
		<p>
			<form:label path="postTitle">Title: </form:label>
			<form:input type="text" path="postTitle" />
		</p>
		<p>
			<form:label path="postTitle">Description: </form:label>
			<form:input type="text" path="postDescription" />
		</p>
		
		<input type="submit" value="Create" />
	</form:form>


</body>
</html>