<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">		<title>Dojo Overflow</title>
		<title>Pregunta</title>
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
							<a class="nav-link" href="/questions">Inicio</a>
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
						<h2><c:out value="${question.getQuestion()}" /></h2>
					</div>
					<c:if test="${not empty question.getTags()}">
						<div class="row px-4 pb-2 justify-content-start">
							<h3>Tags:</h3>
							<c:forEach items="${question.getTags()}" var="tag">
								<a class="btn btn-success mx-2" href='/tags/<c:out value="${tag.getId()}" />' role="button"><c:out value="${tag.getSubject()}" /></a>
							</c:forEach>
						</div>
					</c:if>
					<div class="row px-4 py-3 justify-content-center">
						<div class="col-6 pl-5">
						<c:if test="${not empty question.getAnswers()}">
							<table class="table scroll-box">
								<thead class="thead-light">
									<tr>
										<th scope="col">Respuestas</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${question.getAnswers()}" var="answer">		
									<tr>
										<td><c:out value="${answer.getAnswer()}" /></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${empty question.getAnswers()}">
							<h5 class="py-5">Se el primero en responder</h5>
						</c:if>
						</div>
						<div class="col-6">
							<div class="row justify-content-center px-3">
								<h5 class="text-center">Agrega tu respesta:</h5>
							</div>
							<form:form action="/answers/new" method="post" modelAttribute="answerData">
								<form:hidden path="question" value="${question.getId()}"/>
								<div class="row justify-content-center py-3">
									<div class="col-10 p-4 border-0 rounded bg-light">
										<div class="row justify-content-center">
											<div class="col-3 px-3">
												<form:label path="answer"><h6>Respuesta:</h6></form:label>
											</div>
											<div class="col-9 pr-3">
												<form:textarea path="answer" class="w-100" rows="5"/>
											</div>
											<div class="col-12 px-3">
												<p class="text-danger"><form:errors path="answer"/></p>
											</div>
										</div>
									</div>
								</div>
								<div class="row justify-content-center">
									<div class="col-10">
										<div class="row justify-content-end">
											<div class="col-4-offset-4 pr-1">
									    		<input type="submit" value="Submit" class="btn btn-primary"/>
									    	</div>
								    	</div>
								    </div>
							    </div>
							</form:form>	
						</div>
					</div>				
				</div>
			</div>
		</div>
	</body>
</html>