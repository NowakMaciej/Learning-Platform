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
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/user/teacher/">Return</a><br>
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/exam/create">Create Exam</a>
	<p class="primeText">My Exams [${ exams.size() }]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>No of students</th>
			<th>Difficulty</th>
			<th>Created</th>
			<th>Last Updated</th>
			<th>Exercises</th>
			<th>Assign Students</th>
			<th>Actions</th>
			<th>Active?</th>
			<th>Activate</th>
		</tr>
		<c:forEach items="${ exams }" var="exam">
			<tr>
				<td>${ exam.id }</td>
				<td>${ exam.title }</td>
				<td>${ exam.studentExamDtos.size() }</td>
				<td>${ exam.difficultyLevel }</td>
				<td>${ exam.created }</td>
				<td>${ exam.updated }</td>
				<td>${ exam.exerciseDtos.size() }</td>
				<td>
					<button class="actionButton assignButton">
						<a href="${pageContext.request.contextPath}/exam/assign/${ exam.id }">assign</a>
					</button>
				</td>
				<td>
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/exam/${ exam.id }">edit</a>
					</button>
					<button class="actionButton deleteButton">
						<a href="${pageContext.request.contextPath}/exam/delete/${ exam.id }">delete</a>
					</button>
				</td>
				<td>${ exam.active }</td>
				<td>
					<button class="actionButton activateButton">
						<a href="${pageContext.request.contextPath}/exam/activate/${ exam.id }">activate/deactivate</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


