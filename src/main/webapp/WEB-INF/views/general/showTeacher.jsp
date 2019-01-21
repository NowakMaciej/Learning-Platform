<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>showTeacher</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
</head>
<body>
	<div id="main">
		<div id="loggedUser">
			LOGGED USER: ${user.firstname} ${user.surname}, ROLE: ${user.role}
		</div>
		<div id="menu">
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/edit/">Edit profile!</a>
			</button>
			<button class="menuButton">
				<a href="${pageContext.request.contextPath}/user/messages">My messages</a>
			</button>
		</div>
	</div>
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/user/${user.role}/">Return</a><br>
	<div class="${ (user.id == userOfId.id) ? 'hide' : 'show' }">
		<p class="primeText">Send message to ${ userOfId.firstname } ${ userOfId.surname }</p>
		<div class="leftMargin200">
			<form:form method="post" modelAttribute="messageDto" action="${pageContext.request.contextPath}/message/">
				<form:textarea path="text"/>
				<form:errors path="text" cssClass="error"/><br>
				<form:hidden path="receiver.id" value="${ userOfId.id }"/>
				<input class="submitButton assignButton" type="submit" value="Send"/>
			</form:form>
		</div>
	 </div>
</body>
</html>


