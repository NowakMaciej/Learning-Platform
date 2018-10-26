<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learning Platform</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body align="center">
	<h1>Welcome to LingON Learning Platform!</h1>
	<br>
	<h2>Sign in:</h2>
	<p class="error">${ message }</p>
	<form:form method="post" action="${pageContext.request.contextPath}/user/login" modelAttribute="user">
		E-mail: <form:input path="email"/>
		<form:errors path="email" cssClass="error"/><br>
		Password: <form:input type="password" path="password"/>
		<form:errors path="password" cssClass="error"/><br>
		
		<input type="submit" value="Sign in"/>
	</form:form>
	<br><br><br>
	<div>
	New here?
		<button>
			<a href="${pageContext.request.contextPath}/user/register">Sign up</a>
		</button>
	</div>
</body>
</html>


