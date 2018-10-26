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
	<p>
		<button>
			<a href="${pageContext.request.contextPath}/exam/create">Create Exam</a>
		</button>
	</p>
	<p class="prime">All Exams</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Author</th>
			<th>Title</th>
			<th>No of students</th>
			<th>Difficulty Level</th>
			<th>Created</th>
			<th>Last Updated</th>
			<th>No of exercises</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ exams }" var="exam">
			<tr>
				<td>${ exam.id }</td>
				<td>${ exam.teacherDto.user.firstname } ${ exam.teacherDto.user.surname }</td>
				<td>${ exam.title }</td>
				<td>${ exam.studentDtos.size() }</td>
				<td>${ exam.difficultyLevel }</td>
				<td>${ exam.created }</td>
				<td>${ exam.updated }</td>
				<td>${ exam.exerciseDtos.size() }</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/exam/${ exam.id }">edit</a>
					</button>
					<button>
						<a href="${pageContext.request.contextPath}/exam/delete/${ exam.id }">delete</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


