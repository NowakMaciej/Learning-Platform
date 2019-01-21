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
			LOGGED USER: ${student.user.firstname} ${student.user.surname}, ROLE: ${student.user.role}
		</div>
		<div id="menu">
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/edit/${ student.user.id }">Edit profile</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/messages">My messages</a>
			</button>
		</div>
	</div>
	<p class="primeText">My teacher</p>
	<table class="table">
		<tr>
			<th>Teacher Id</th>
			<th>Name</th>
			<th>Actions</th>
		</tr>
		<tr>
			<td>${ student.teacher.id }</td>
			<td>${ student.teacher.user.firstname } ${ student.teacher.user.surname }</td>
			<td>
				<button class="actionButton messageButton">
					<a href="${pageContext.request.contextPath}/user/message/${student.teacher.user.id}">message</a>
				</button>
			</td>
		</tr>
	</table>
	<p class="primeText">My Exams [${ exams.size() }]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Author</th>
			<th>Title</th>
			<th>Difficulty Level</th>
			<th>No of exercises</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ exams }" var="studentExam">
			<tr>
				<td>${ studentExam.examDto.id }</td>
				<td>${ studentExam.examDto.teacherDto.user.firstname } ${ studentExam.examDto.teacherDto.user.surname }</td>
				<td>${ studentExam.examDto.title }</td>
				<td>${ studentExam.examDto.difficultyLevel }</td>
				<td>${ studentExam.examDto.exerciseDtos.size() }</td>
				<td>
					<button class="actionButtonTakeViewExam ${ studentExam.active ? 'show' : 'hide' } ">
						<a href="${pageContext.request.contextPath}/exam/take/${ studentExam.examDto.id }">take exam</a>
					</button>
					<button class=" actionButtonTakeViewExam ${ studentExam.active ? 'hide' : 'show' }  ">
						<a href="${pageContext.request.contextPath}/exam/view/${ studentExam.id }">view</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


