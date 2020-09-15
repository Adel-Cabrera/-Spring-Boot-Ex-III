<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Event</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
    <div class="container">
        <a href="/events">Back</a>
        <h1>${event.name}</h1>
        <div class="col-md-6">
            <h3>Edit Event</h3>
            <form:form method="POST" action="/events/${event.id}/edit" modelAttribute="emptyevent">
                <p><form:label path="name">Name: </form:label><form:input path="name"/> (previously: ${event.name})</p>
                <p><form:label path="eventDate">Date: </form:label><form:input type="date" path="eventDate"/>  (previously: <fmt:formatDate pattern = "MM/dd/yyyy" value="${event.eventDate}"></fmt:formatDate>)</p>
                <p><form:label path="city">Location: </form:label>
                    <form:input path="city"/>
                    <form:select path="state">
                        <c:forEach items="${states}" var="st">
                            <form:option path="state" value="${st}"></form:option>
                        </c:forEach>
                    </form:select>
                    (previously: ${event.city}, ${event.state})
                </p>  
                <input type="submit" value="Edit"/>
            </form:form>   
        </div>  
        <div class="col-md-6"><p><form:errors path="emptyevent.*"/></p></div>
    </div>
</body>
</html>