<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit exercise</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<button>
		<a href="${pageContext.request.contextPath}/user/teacher/">Abort</a>
	</button>
	<form:form method="post" modelAttribute="exerciseDto">
		<form:hidden path="id" value="${ exercise.id }"/>
		<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
		Question<br>
		<form:textarea path="text" rows="2" cols="40"/>
		<form:errors path="text" cssClass="error" /><br>
		Answer:<br>
		<form:input path="answer" />
		<form:errors path="answer" cssClass="error" /><br>
		Difficulty level:<br>
		<form:select path="difficultyLevel" items="${ difficultyLevels }" itemValue="description" itemLabel="description"/><br>
		Categories:<br>
		<form:select path="categoryDtos" items="${ categories }" itemValue="id" itemLabel="name"/><br>
		<input type="submit" value="Edit" />
	</form:form>
</body>
</html>


