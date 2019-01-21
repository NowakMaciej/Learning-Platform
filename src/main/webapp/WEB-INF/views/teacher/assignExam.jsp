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
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/exam/">Return</a><br>
	<p class="primeText">Assign students:</p>
	<div class="leftMargin200">
		<form method="post" action="${pageContext.request.contextPath}/exam/assign/${examDto.id}">
			<table>
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>Student Level</th>
					<th>Select</th>
				</tr>
				<c:forEach items="${ students }" var="student">
					<tr>
						<td> ${ student.id } </td>
						<td> ${ student.name } </td>
						<td> ${ student.difficultyLevel } </td>
						<td>
							<input type="checkbox" name="listOfStudents" value="${ student.id }"/>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input class="submitButton assignButton" type="submit" value="Assign">
		</form>
	</div>
</body>
</html>


