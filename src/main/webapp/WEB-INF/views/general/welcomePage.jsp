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
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
</head>
<body>
	<h1>Welcome to LingON Learning Platform!</h1>
	<br>
	<h2>Sign in:</h2>
	<p class="${ not empty message ? 'failure' : 'readNot' }">${ message }</p>
	<div class="mainContainer">
		<form:form method="post" action="${pageContext.request.contextPath}/user/login" modelAttribute="user">
			<div>
				<span>E-mail:</span>
				<form:input path="email"/>
				<form:errors path="email" cssClass="error"/>
			</div>
			<div>
				<span>Password:</span>
				<form:input type="password" path="password"/>
				<form:errors path="password" cssClass="error"/>
			</div>
			<div>
				<input class="signInButton" type="submit" value="Sign in"/>
			</div>
		</form:form>
		<br>
		<div class="center">
			<span>New here?</span>
			<a class="signUpButton" href="${pageContext.request.contextPath}/user/registerStudent">Sign up</a>
		</div>
	</div>
</body>
</html>


