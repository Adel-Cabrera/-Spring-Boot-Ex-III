<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title><c:out value="${person.getFirstName()} ${getLastName}" /></title>
	</head>
	<body>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 p-3">
					<div class="row justify-content-center border-bottom pb-2">
						<h2 class="text-center"><c:out value="${person.getFirstName()} ${person.getLastName()}" /></h2>
					</div>
					<div class="row justify-content-center">
						<div class="col-8 p-2">
							<div class="row justify-content-start">
								<div class="col-6">
							        <h6>License Number:</h6>
						        </div>
						        <div class="col-6">
						        	<p><c:out value="${person.getLicense().getNumber()}" /></p>
						        </div>
							</div>
							<div class="row justify-content-start">
								<div class="col-6">
							        <h6>State:</h6>
						        </div>
						        <div class="col-6">
						        	<p><c:out value="${person.getLicense().getState()}" /></p>
						        </div>
							</div>
							<div class="row justify-content-start">
								<div class="col-6">
							        <h6>Expiration Date:</h6>
						        </div>
						        <div class="col-6">
						        	<p><c:out value="${person.getLicense().getExpirationDate()}" /></p>
						        </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>