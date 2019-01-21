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
	<h2>Edit teacher</h2>
	<div class="container">
		<form:form method="post" modelAttribute="teacherDto" action="${pageContext.request.contextPath}/user/edit/teacher">
			<form:hidden path="user.id" value="${ teacher.user.id }"/>
			<form:hidden path="id" value="${ teacher.id }"/>
			<form:hidden path="user.role" value="${ teacher.user.role }"/>
			<div>
				<span class="formSpan">Firstname:</span>
				<form:input path="user.firstname"/>
				<form:errors path="user.firstname" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">Surname:</span>
				<form:input path="user.surname"/>
				<form:errors path="user.surname" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">E-mail:</span>
				<form:input path="user.email"/>
				<form:errors path="user.email" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">Password:</span>
				<form:input path="user.password"/>
				<form:errors path="user.password" cssClass="error"/>
			</div>
			<div>
				<input class="submitButton" type="submit" value="Submit"/>
				<a class="abortButton" href="${pageContext.request.contextPath}/user/admin/">Abort</a>
			</div>
		</form:form>
	</div>
</body>
</html>


