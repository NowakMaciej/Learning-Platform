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
	<p class="login">LOGGED USER: ${teacher.user.firstname} ${teacher.user.surname}, ROLE: ${teacher.user.role}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/edit/${ teacher.user.id }">Edit
			profile</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/messages">My
			messages</a>
	</button>
	<br>
	<p>
		<button>
			<a href="${pageContext.request.contextPath}/category/">Manage Categories</a>
		</button>
	</p>
	<p>
		<button>
			<a href="${pageContext.request.contextPath}/exercise/">Manage Exercises</a>
		</button>
	</p>
	<p>
		<button>
			<a href="${pageContext.request.contextPath}/exam/">Manage Exams</a>
		</button>
	</p>
	<p class="prime">My Students</p>
	<table>
		<tr>
			<th>Student Id</th>
			<th>Name</th>
			<th>Level</th>
		</tr>
		<c:forEach items="${ students }" var="student">
			<tr>
				<td>${ student.id }</td>
				<td><a
					href="${pageContext.request.contextPath}/user/message/${student.user.id}">
						${ student.user.firstname } ${ student.user.surname }</a></td>
				<td>${ student.difficultyLevel }</td>
			</tr>
		</c:forEach>
	</table>
	<p class="prime">My Exams [last 5]</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>No of students</th>
			<th>Difficulty Level</th>
			<th>Created</th>
			<th>Last Updated</th>
			<th>No of exercises</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ lastExams }" var="lastExam">
			<tr>
				<td>${ lastExam.id }</td>
				<td>${ lastExam.title }</td>
				<td>${ lastExam.studentDtos.size() }</td>
				<td>${ lastExam.difficultyLevel }</td>
				<td>${ lastExam.created }</td>
				<td>${ lastExam.updated }</td>
				<td>${ lastExam.exerciseDtos.size() }</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/exam/${ lastExam.id }">edit</a>
					</button>
					<button>
						<a href="${pageContext.request.contextPath}/exam/delete/${ lastExam.id }">delete</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="error">${ teacher.user }</p>
	<p class="error">${ teacher }</p>
</body>
</html>


