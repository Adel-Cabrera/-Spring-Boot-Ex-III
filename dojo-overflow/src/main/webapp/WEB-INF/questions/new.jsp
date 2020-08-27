<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">		<title>Dojo Overflow</title>
		<title>Nueva pregunta</title>
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
							<a class="nav-link" href="/questions/new">Nueva pregunta <span class="sr-only">(current)</span></a>
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
						<h1>Cuál es tu pregunta?</h1>
					</div>
					<form:form action="/questions/new" method="post" modelAttribute="questionTags">
						<div class="row justify-content-center p-3">
							<div class="col-10 p-4 border-0 rounded bg-light">
								<div class="row justify-content-center">
									<div class="col-4 px-4">
										<form:label path="question"><h5>Preguntas:</h5></form:label>
									</div>
									<div class="col-8 pr-4">
										<form:textarea path="question" class="w-100" rows="5"/>
									</div>
									<div class="col-12 px-4">
										<p class="text-danger"><form:errors path="question"/></p>
									</div>
								</div>
								<div class="row justify-content-center">
									<div class="col-4 px-4">
										<form:label path="tags"><h5>Tags:</h5></form:label>
									</div>
									<div class="col-8 pr-4">
										<form:input type="text" path="tags" class="w-100" />
									</div>
									<div class="col-12 px-4">
										<p class="text-danger"><form:errors path="tags"/></p>
									</div>
								</div>
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="col-10 px-4">
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
	</body>
</html>