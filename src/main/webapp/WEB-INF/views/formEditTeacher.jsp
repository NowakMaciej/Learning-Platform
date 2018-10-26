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
		<a href="${pageContext.request.contextPath}/user/${ teacher.user.role }/">Abort</a>
	</button>
	<form:form method="post" modelAttribute="teacherDto" action="${pageContext.request.contextPath}/user/edit/teacher">
		<form:hidden path="user.id" value="${ teacher.user.id }"/>
		Firstname:<form:input path="user.firstname"/>
		<form:errors path="user.firstname" cssClass="error"/><br>
		Surname:<form:input path="user.surname"/>
		<form:errors path="user.surname" cssClass="error"/><br>
		E-mail:<form:input path="user.email"/>
		<form:errors path="user.email" cssClass="error"/><br>
		Password:<form:input path="user.password"/>
		<form:errors path="user.password" cssClass="error"/><br>
		<form:hidden path="user.role" value="${ teacher.user.role }"/>
		<form:hidden path="id" value="${ teacher.id }"/>
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>


