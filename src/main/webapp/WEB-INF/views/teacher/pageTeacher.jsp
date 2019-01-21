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
	<br>
	<div class="mainFunctions">
		<div class="functionalButtons">
			<button class="functionalButton">
				<a href="${pageContext.request.contextPath}/category/">Manage Categories</a>
			</button>
			<button class="functionalButton">
				<a href="${pageContext.request.contextPath}/exercise/">Manage Exercises</a>
			</button>
			<button class="functionalButton">
				<a href="${pageContext.request.contextPath}/exam/">Manage Exams</a>
			</button>
		</div>
	</div>
	<p class="primeText">My Students</p>
	<table class="table">
		<tr>
			<th>Student Id</th>
			<th>Name</th>
			<th>Level</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ students }" var="student">
			<tr>
				<td>${ student.id }</td>
				<td>${ student.user.firstname } ${ student.user.surname }</td>
				<td>${ student.difficultyLevel }</td>
				<td>
					<button class="actionButton messageButton">
						<a href="${pageContext.request.contextPath}/user/message/${student.user.id}">message</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="primeText">My Exams [last 5]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>Students</th>
			<th>Level</th>
			<th>Created</th>
			<th>Last Updated</th>
			<th>Exercises</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ lastExams }" var="lastExam">
			<tr>
				<td>${ lastExam.id }</td>
				<td>${ lastExam.title }</td>
				<td>${ lastExam.studentExamDtos.size() }</td>
				<td>${ lastExam.difficultyLevel }</td>
				<td>${ lastExam.created }</td>
				<td>${ lastExam.updated }</td>
				<td>${ lastExam.exerciseDtos.size() }</td>
				<td>
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/exam/${ lastExam.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


