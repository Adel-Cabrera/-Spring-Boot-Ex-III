<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- To use form binding -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>Product Page</title>
	</head>
	<body>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 p-3">
					<div class="row justify-content-center border-bottom pb-2">
						<h2 class="text-center"><c:out value="${product.getName()}" /></h2>
					</div>
					<div class="row justify-content-center">
						<div class="col-6 p-2">
							<div class="row">
								<h3 class="text-start">Categories:</h3>
							</div>
							<c:if test="${not empty product.getCategories()}" >
							<div class="row">
								<ul>
								<c:forEach items="${product.getCategories()}" var="category">
									<li><c:out value="${category.getName()}" /></li>								
								</c:forEach>
								</ul>
							</div>
							</c:if>
						</div>
						<div class="col-6 p-2">
						<c:if test="${not empty categories}" >
							<form action='/products/<c:out value="${product.getId()}" />' method="post">
								<input type="hidden" name="_method" value="put">
								<div class="row">
									<div class="col-6">
										<label for="category">Add Category:</label>
									</div>
									<div class="col-6">
										<select name="id">
											<c:forEach items="${categories}" var="category">
												<option value="${category.getId()}" label="${category.getName()}" />
											</c:forEach>										
										</select>
									</div>
								</div>
								<div class="row justify-content-center">
									<div class="col-2">
							    		<input type="submit" value="Submit" class="btn btn-success"/>
							    	</div>
							    </div>
							</form>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>