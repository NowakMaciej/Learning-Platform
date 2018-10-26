<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learning Platform</title>
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
</head>
<body>
	<p class="login">LOGGED USER: ${teacher.user.firstname} ${teacher.user.surname},
		ROLE: ${teacher.user.role}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/teacher/">Return</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/edit">Edit
			profile</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/messages">My
			messages</a>
	</button>
	<p class="prime">Add new Exercise:</p>
	<form:form method="post" modelAttribute="exerciseDto" action="${pageContext.request.contextPath}/exercise/">
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
		<input type="submit" value="Add exercise" />
	</form:form>
	<br>
	<p class="prime">All Exercises</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Author</th>
			<th>Text</th>
			<th>Answer</th>
			<th>Difficulty Level</th>
			<th>Created</th>
			<th>Last Updated</th>
			<th>Categories</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ lastExercises }" var="lastExercise">
			<tr>
				<td>${ lastExercise.id }</td>
				<td>${ lastExercise.teacherDto.user.firstname } ${ lastExercise.teacherDto.user.surname }</td>
				<td>${ lastExercise.text }</td>
				<td>${ lastExercise.answer }</td>
				<td>${ lastExercise.difficultyLevel }</td>
				<td>${ lastExercise.created }</td>
				<td>${ lastExercise.updated }</td>
				<td>
					<c:forEach items="${ lastExercise.categoryDtos }" var="categoryDto">
						<c:out value="${ categoryDto.name }"/><br>
					</c:forEach>
				</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/exercise/${ lastExercise.id }">edit</a>
					</button>
					<button>
						<a href="${pageContext.request.contextPath}/exercise/delete/${ lastExercise.id }">delete</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


