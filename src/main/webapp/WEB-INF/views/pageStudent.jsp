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
	<p class="login">LOGGED USER: ${student.user.firstname} ${student.user.surname}, ROLE: ${student.user.role}</p>
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
	<p class="prime">My teacher</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
		</tr>
		<tr>
			<td>TEACHER ID: ${ student.teacher.id }
				TEACHER USER ID: ${ student.teacher.user.id }
			</td>
			<td><a
				href="${pageContext.request.contextPath}/user/${student.teacher.user.id}">
					${ student.teacher.user.firstname } ${ student.teacher.user.surname }</a></td>
		</tr>
	</table>
	<p class="prime">My Exams [${ exams.size() }]</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Author</th>
			<th>Title</th>
			<th>Difficulty Level</th>
			<th>No of exercises</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ exams }" var="exam">
			<tr>
				<td>${ exam.id }</td>
				<td>${ exam.teacherDto.user.firstname } ${ exam.teacherDto.user.surname }</td>
				<td>${ exam.title }</td>
				<td>${ exam.difficultyLevel }</td>
				<td>${ exam.exerciseDtos.size() }</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/exam/take/${ exam.id }">take exam</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="error">${ student }</p>
</body>
</html>


