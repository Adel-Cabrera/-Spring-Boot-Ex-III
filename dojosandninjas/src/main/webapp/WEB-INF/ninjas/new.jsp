<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>New Ninja</title>
	</head>
	<body>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 p-3">
					<div class="row justify-content-center border-bottom pb-2">
						<h2 class="text-center">New Ninja</h2>
					</div>
					<div class="row justify-content-center">
						<div class="col-6 p-2">
							<form:form action="/ninjas/new" method="post" modelAttribute="ninja">
								<div class="row justify-content-start">
									<div class="col-6">
								        <form:label path="dojo">Dojo:</form:label>
							        </div>
							        <div class="col-6">
							        	<form:select path="dojo">
							        		<c:forEach items="${dojos}" var="dojo">
							        			<form:option value="${dojo.getId()}" label="${dojo.getName()}" />
							        		</c:forEach>
							        	</form:select>	
							        </div>
							        <div class="col-12">
										<p class="text-danger"><form:errors path="dojo"/></p>
									</div>
								</div>
								<div class="row justify-content-start">
									<div class="col-6">
								        <form:label path="firstName">First Name:</form:label>
								    </div>
							        <div class="col-6">  
							        	<form:input path="firstName" />
									</div>
									<div class="col-12">
										<p class="text-danger"><form:errors path="firstName"/></p>
									</div>
								</div>
								<div class="row justify-content-start">
									<div class="col-6">
								        <form:label path="lastName">Last Name:</form:label>
								    </div>
							        <div class="col-6">  
							        	<form:input path="lastName" />
									</div>
									<div class="col-12">
										<p class="text-danger"><form:errors path="lastName"/></p>
									</div>
								</div>
								<div class="row justify-content-start">
									<div class="col-6">
								        <form:label path="age">Select Age:</form:label>
								    </div>
							        <div class="col-6">  
							        	<form:input path="age" />
									</div>
									<div class="col-12">
										<p class="text-danger"><form:errors path="age"/></p>
									</div>
								</div>
								<div class="row justify-content-center">
									<div class="col-2">
							    		<input type="submit" value="Submit" class="btn btn-success"/>
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