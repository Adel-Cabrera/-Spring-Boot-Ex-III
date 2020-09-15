<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login/Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="col-md-6">
            <h1>Login</h1>
            <form method="POST" action="/login">
                <p>Email: <input type="text" id="email" name="username"/></p>
                <p>Password: <input type="password" id="password" name="password"/></p>
                <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Login!"/>
            </form>
            <br>
            <c:if test="${logError != null}">
                <p>${logError}</p>
            </c:if>
            <c:if test="${logout != null}">
                <p><c:out value="${logout}"></c:out></p>
            </c:if>
        </div>
        <div class="col-md-6">
            <h1>Register!</h1>
            <form:form method="POST" action="/register" modelAttribute="user">
                <p><form:label path="first">First Name: </form:label><form:input path="first"/></p>
                <p><form:label path="last">Last Name: </form:label><form:input path="last"/></p>
                <p><form:label path="email">Email: </form:label><form:input path="email"/></p>
                <p><form:label path="city">Location:</form:label>
                    <form:input path="city"/>
                    <form:label path="state"></form:label>
                    <form:select path="state">
                        <c:forEach items="${states}" var="st">
                            <form:option path="state" value="${st}"></form:option>
                        </c:forEach>
                    </form:select>
                </p>                <p><form:label path="password">Password: </form:label><form:password path="password"/></p>
                <p><form:label path="confirm">Confirm Password: </form:label><form:password path="confirm"/></p>
                <input type="submit" value="Register!"/>
            </form:form>
            <br>
            <p><form:errors path="user.*"/></p>
        </div>
    </div>
</body>
</html>