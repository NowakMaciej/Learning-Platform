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
			
			<form:hidden path="role" value="student"/>
			<!--
			<div>
				<span class="formSpan">Role:</span>
				<form:select class="formSelect" path="role" items="${roles}" itemValue="userRole" itemLabel="userRole"/>
			</div>
			-->
			<!--
			<div>
				<span class="formSpan">Teacher:</span>
			<form:select class="formSelect" path="teacher.id" items="${teachers}" itemValue="id" itemLabel="name"/>
			</div>
			<div>
				<span class="formSpan">Difficulty level:</span>
				<form:select class="formSelect" path="difficultyLevel" items="${ difficultyLevels }" itemValue="description" itemLabel="description"/>
			-->
			<div>
				<input class="submitButton" type="submit" value="Submit"/>
				<a class="abortButton" href="${pageContext.request.contextPath}/">Abort</a>
			</div>
		</form:form>
	</div>
</body>
</html>


