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
			LOGGED USER: ${admin.firstname} ${admin.surname}, ROLE: ${admin.role}
		</div>
		<div id="menu">
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/edit/${ admin.id }">Edit profile</a>
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
				<a href="${pageContext.request.contextPath}/user/registerTeacher">Add teacher</a>
			</button>
		</div>
	</div>
	<br>
	<p class="primeText">Unassigned Students [${ unassignedStudents.size() }]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Active</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ unassignedStudents }" var="unassignedStudent">
			<tr>
				<td>${ unassignedStudent.id }</td>
				<td>${ unassignedStudent.user.firstname }</td>
				<td>${ unassignedStudent.user.email }</td>
				<td>${ unassignedStudent.user.active }</td>
				<td>
					<button class="actionButton activateButton">
						<a href="${pageContext.request.contextPath}/user/activation/${ unassignedStudent.user.id }">activate/deactivate</a>
					</button>
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/user/edit/${ unassignedStudent.user.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="primeText">Teachers [${ teachers.size() }]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Active</th>
			<th>Students</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ teachers }" var="teacher">
			<tr>
				<td>${ teacher.id }</td>
				<td>${ teacher.name }</td>
				<td>${ teacher.user.email }</td>
				<td>${ teacher.user.active }</td>
				<td>${ teacher.students.size() }</td>
				<td>
					<button class="actionButton activateButton">
						<a href="${pageContext.request.contextPath}/user/activation/${ teacher.user.id }">activate/deactivate</a>
					</button>
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/user/edit/${ teacher.user.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="primeText">Students [${ students.size() }]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Active</th>
			<th>Level</th>
			<th>Teacher</th>
			<th>Actions</th>
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
					<button class="actionButton activateButton">
						<a href="${pageContext.request.contextPath}/user/activation/${ student.user.id }">activate/deactivate</a>
					</button>
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/user/edit/${ student.user.id }">edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


