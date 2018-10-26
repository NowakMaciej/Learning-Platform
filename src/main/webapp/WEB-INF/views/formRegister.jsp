<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formArticle</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<button>
		<a href="${pageContext.request.contextPath}/">Abort</a>
	</button>
	<form:form method="post" modelAttribute="userDto">
		Firstname:<form:input path="firstname"/>
		<form:errors path="firstname" cssClass="error"/><br>
		Surname:<form:input path="surname"/>
		<form:errors path="surname" cssClass="error"/><br>
		E-mail:<form:input path="email"/>
		<form:errors path="email" cssClass="error"/><br>
		Password:<form:input path="password"/>
		<form:errors path="password" cssClass="error"/><br>
		Role:<br>
		<form:select path="role" items="${roles}" itemValue="userRole" itemLabel="userRole"/><br>
		Teacher:<br>
		<form:select path="teacher.id" items="${teachers}" itemValue="id" itemLabel="name"/><br>
		Difficulty level:
		<form:select path="difficultyLevel" items="${ difficultyLevels }" itemValue="description" itemLabel="description"/><br>
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>


