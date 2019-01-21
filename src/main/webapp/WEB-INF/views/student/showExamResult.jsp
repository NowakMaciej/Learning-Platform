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
	<p class="primeText"> your result: ${ result } / ${ studentExamDto.examDto.exerciseDtos.size() }</p>
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/user/student/">Ok</a>
</body>
</html>


