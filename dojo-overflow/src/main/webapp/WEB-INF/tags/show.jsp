<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<title>Preguntas</title>
	</head>
	<body class="bg-light">
		<div class="container">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<span class="navbar-brand mb-0 h1">Dojo Overflow</span>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active">
							<a class="nav-link" href="/questions">Inicio <span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/questions/new">Nueva pregunta</a>
						</li>
					</ul>
					<form class="form-inline my-2 my-lg-0" action="/tags/q" method="get">
					    <input class="form-control mr-sm-2"placeholder="Search" aria-label="Search" name="subject">
					    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
			    	</form>
				</div>
			</nav>
			<div class="row justify-content-center">
				<div class="col bg-white p-3">
					<div class="row p-4">
						<h1>Asunto: <c:out value="${subject}" /></h1>
					</div>
					<div class="row justify-content-center px-4">
						<c:if test="${not empty questions}">
							<table class="table scroll-box">
								<thead class="thead-light">
									<tr>
										<th scope="col">Pregunta</th>
										<th scope="col">Tags</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${questions}" var="question">		
									<tr>
										<td><a href='/questions/<c:out value="${question.getId() }" />'><c:out value="${question.getQuestion()}" /></a></td>
										<td>
											<c:forEach items="${question.getTags()}" var="tag">
												<c:out value="${tag.getSubject()} " />
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${empty questions}" >
							<hr>
							<div class="col p-3">
								<h3 class="text-center">Se el primero en preguntar</h3>
							</div>							
						</c:if>
					</div>
					<hr>
					<div class="row justify-content-end px-3">
						<a class="btn btn-primary btn-md" href="questions/new" role="button">Nueva pregunta</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>