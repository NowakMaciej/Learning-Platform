<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formArticle</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
</head>
<body>
	<h2>Add teacher</h2>
	<div class="container">
		<form:form method="post" modelAttribute="userDto">
			<div>
				<span class="formSpan">Firstname:</span>
				<form:input path="firstname"/>
				<form:errors path="firstname" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">Surname:</span>
				<form:input path="surname"/>
				<form:errors path="surname" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">E-mail:</span>
				<form:input path="email"/>
				<form:errors path="email" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">Password:</span>
				<form:input path="password"/>
				<form:errors path="password" cssClass="error"/>
			</div>
			<form:hidden path="role" value="teacher"/>
			<div>
				<input class="submitButton" type="submit" value="Submit"/>
				<a class="abortButton" href="${pageContext.request.contextPath}/user/admin/">Abort</a>
			</div>
		</form:form>
	</div>
</body>
</html>


