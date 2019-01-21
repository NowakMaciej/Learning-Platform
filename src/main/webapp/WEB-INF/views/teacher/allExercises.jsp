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
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css" rel="stylesheet">
</head>
<body>
	<div id="main">
		<div id="loggedUser">
			LOGGED USER: ${teacher.user.firstname} ${teacher.user.surname}, ROLE: ${teacher.user.role}
		</div>
		<div id="menu">
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/edit/${ teacher.user.id }">Edit profile</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/messages">My messages</a>
			</button>
		</div>
	</div>
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/user/teacher/">Return</a>
	<p class="primeText">Add new Exercise:</p>
	<div class="leftMargin">
		<form:form method="post" modelAttribute="exerciseDto" action="${pageContext.request.contextPath}/exercise/">
			<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
			<div>
				<span class="formSpan">Question</span>
				<form:textarea path="text"/>
				<form:errors path="text" cssClass="error" />
			</div>
			<div>
				<span class="formSpan">Answer:</span>
				<form:textarea path="answer"/>
				<form:errors path="answer" cssClass="error" />
			</div>
			<div>
				<span class="formSpan">Difficulty level:</span>
				<form:select class="formSelect" path="difficultyLevel"
				items="${ difficultyLevels }" itemValue="description" itemLabel="description"/>
			</div>
			<div>
				<span class="formSpan">Categories:</span>
				<form:select class="formSelect" path="categoryDtos" items="${ categories }" itemValue="id" itemLabel="name"/>
			</div>
			<input class="submitButton addButton" type="submit" value="Add"/>
		</form:form>
	</div>
	<br>
	<p class="primeText">All Exercises</p>
	<table class="table">
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
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/exercise/${ lastExercise.id }">edit</a>
					</button>
					<button class="actionButton deleteButton">
						<a href="${pageContext.request.contextPath}/exercise/delete/${ lastExercise.id }">delete</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


