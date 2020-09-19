<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post detail</title>
</head>
<body>
	<p>${creatorPost.username}</p>
	<p>${post.postTitle}</p>
	<p>${post.postDescription}</p>
	
<%-- 	<p>${postDescription}</p> --%>
<a href="/posts">Go back</a>

</body>
</html>