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
	<p class="login">LOGGED USER: ${admin.firstname} ${admin.surname}, ROLE: ${admin.role}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/edit/${ admin.id }">Edit
			profile</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/messages">My
			messages</a>
	</button>
	<br>
	<button>
		<a href="${pageContext.request.contextPath}/user/registerTeacher">Add teacher</a>
	</button>
	<br>
	<p class="prime">Unassigned Students</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Active</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${ unassignedStudents }" var="unassignedStudent">
			<tr>
				<td>${ unassignedStudent.id }</td>
				<td>${ unassignedStudent.user.firstname }</td>
				<td>${ unassignedStudent.user.email }</td>
				<td>${ unassignedStudent.user.active }</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/user/activation/${ unassignedStudent.user.id }">activate/deactivate</a>
					</button>
					<button>
						<a href="${pageContext.request.contextPath}/user/edit/${ unassignedStudent.user.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="prime">Teachers</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Active</th>
			<th>No. of students</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${ teachers }" var="teacher">
			<tr>
				<td>${ teacher.id }</td>
				<td>${ teacher.name }</td>
				<td>${ teacher.user.email }</td>
				<td>${ teacher.user.active }</td>
				<td>${ teacher.students.size() }</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/user/activation/${ teacher.user.id }">activate/deactivate</a>
					</button>
					<button>
						<a href="${pageContext.request.contextPath}/user/edit/${ teacher.user.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="prime">Students</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Active</th>
			<th>Difficulty Level</th>
			<th>Teacher</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${ students }" var="student">
			<tr>
				<td>${ student.id }</td>
				<td>${ student.name }</td>
				<td>${ student.user.email }</td>
				<td>${ student.user.active }</td>
				<td>${ student.difficultyLevel }</td>
				<td>${ student.teacher.name }</td>
				<td>
					<button>
						<a href="${pageContext.request.contextPath}/user/activation/${ student.user.id }">activate/deactivate</a>
					</button>
					<button>
						<a href="${pageContext.request.contextPath}/user/edit/${ student.user.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="error">${ admin }</p>
</body>
</html>


