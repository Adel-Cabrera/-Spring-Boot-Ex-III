<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- To use form binding -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>Category Page</title>
	</head>
	<body>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 p-3">
					<div class="row justify-content-center border-bottom pb-2">
						<h2 class="text-center"><c:out value="${category.getName()}" /></h2>
					</div>
					<div class="row justify-content-center">
						<div class="col-6 p-2">
							<div class="row">
								<h3 class="text-start">Products:</h3>
							</div>
							<c:if test="${not empty category.getProducts()}" >
							<div class="row">
								<ul>
								<c:forEach items="${category.getProducts()}" var="product">
									<li><c:out value="${product.getName()}" /></li>								
								</c:forEach>
								</ul>
							</div>
							</c:if>
						</div>
						<div class="col-6 p-2">
						<c:if test="${not empty products}" >
							<form action='/categories/<c:out value="${category.getId()}" />' method="post">
								<input type="hidden" name="_method" value="put">
								<div class="row">
									<div class="col-6">
										<label for="category">Add Product:</label>
									</div>
									<div class="col-6">
										<select name="id">
											<c:forEach items="${products}" var="product">
												<option value="${product.getId()}" label="${product.getName()}" />
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