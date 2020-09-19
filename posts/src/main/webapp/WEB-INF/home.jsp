<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	<h2>Your posts</h2>

	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Description</th>
				<th>Actions:</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${currentUserPosts}" var="post">
				<tr>
					<td><a href="/posts/${post.id}">${post.postTitle}</a></td>
					<td>${post.postDescription}</td>
					<td>
						<a href="/posts/edit/${post.id}">Edit</a>
						<a href="/posts/delete/${post.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/posts/new">New post</a>


	<br />
	<br />
	<h2>All posts</h2>
	<table>
		<thead>
			<tr>
				<th>Title</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allPosts}" var="post">
<%-- 			${post.creatorPost.id } --%>
				<tr>
					<td><a href="/posts/${post.id}">${post.postTitle}</a></td>
					<td>${post.postDescription}
						<c:if test="${currentUser.id == post.creatorPost.id}">
							<a href="/posts/edit/${post.id}">Edit</a><a href="/posts/delete/${post.id}">Delete</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>