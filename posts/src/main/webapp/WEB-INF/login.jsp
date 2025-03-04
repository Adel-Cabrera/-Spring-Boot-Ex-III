<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>

    <c:if test="${logoutMessage != null}">
        <c:out value="${logoutMessage}"></c:out>
    </c:if>
    <c:if test="${errorMessage != null}">
        <c:out value="${errorMessage}"></c:out>
    </c:if>
    
    <h1>Login</h1>
    <form method="POST" action="/login">
        <p>
            <label for="username">Email</label>
            <input type="text" id="username" name="username"/>
        </p>
<!--         <p> -->
<!--             <label for="email">email</label> -->
<!-- 			<input type="text" id="email" name="email"/> -->
            
<!--             <input type="email" id="email" name="email"/> -->
            
<!--         </p>         -->
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Login!"/>
    </form>
    
    <a href="/registration">Registration</a>
</body>
</html>
