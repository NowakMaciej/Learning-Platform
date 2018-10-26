<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit exam</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<button>
		<a href="${pageContext.request.contextPath}/user/teacher/">Abort</a>
	</button>
	<form:form method="post" modelAttribute="examDto">
		<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
		Title<br>
		<form:textarea path="title" rows="2" cols="40"/>
		<form:errors path="title" cssClass="error" /><br>
		Difficulty level:<br>
		<form:select path="difficultyLevel" items="${ difficultyLevels }" itemValue="description" itemLabel="description"/><br>
		Students:<br>
		<form:select path="studentDtos" items="${students}" itemValue="id" itemLabel="name"/><br>
		Exercises:<br>
			<table>
			<tr>
				<th>Id</th>
				<th>Text</th>
				<th>Answer</th>
				<th>Difficulty Level</th>
				<th>Categories</th>
				<th>Select</th>
			</tr>
			<c:forEach items="${ exercises }" var="exercise">
				<tr>
					<td>${ exercise.id }</td>
					<td>${ exercise.teacherDto.user.firstname } ${ exercise.teacherDto.user.surname }</td>
					<td>${ exercise.text }</td>
					<td>${ exercise.answer }</td>
					<td>${ exercise.difficultyLevel }</td>
					<td>
						<form:checkbox path="exerciseDtos" value="${ exercise.id }"/>
					</td>
				</tr>
			</c:forEach>
			</table><br>
			<input type="submit" value="Edit" />
	</form:form>
</body>
</html>


