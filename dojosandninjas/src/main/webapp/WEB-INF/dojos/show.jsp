<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title><c:out value="${dojo.getName()}" /></title>
	</head>
	<body>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 p-3">
					<div class="row justify-content-center bg-white border rounded-bottom">
						<c:if test="${not empty dojo.getNinjas()}" >
							<div class="col-8 p-3">
								<div class="row justify-content-center border-bottom pb-2">
									<h2 class="text-center"><c:out value="${dojo.getName()}" /> Ninjas</h2>
								</div>
								<div class="row justify-content-center">
							        <table class="table">
							        	<thead>
							        		<tr>
								        		<th scope="col">First Name</th>
								        		<th scope="col">Last Name</th>
								        		<th scope="col">Age</th>
								        	</tr>					        		
							        	</thead>
							        	<c:forEach items="${dojo.getNinjas()}" var="ninja">								    
									        <tr>
									            <td><c:out value="${ninja.getfirstName()}"/></td>
									            <td><c:out value="${ninja.getlastName()}"/></td>
									            <td><c:out value="${ninja.getAge()}"/></td>
									        </tr>
							        	</c:forEach>
							        </table>
								</div>
							</div>
						</c:if>
						<c:if test="${empty dojo.getNinjas()}" >
							<div class="row justify-content-center p-5">						
								<h1 class="text-center">No ninjas added yet...</h1>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>