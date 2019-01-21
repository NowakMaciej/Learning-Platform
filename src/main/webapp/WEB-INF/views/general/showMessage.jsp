<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Warsztaty 6</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css" rel="stylesheet">
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
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/user/messages">Return</a><br>
	<p class="primeText">Message details</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>From</th>
			<th>To</th>
			<th>Text</th>
			<th>is Read?</th>
		</tr>
		<tr>
			<td> ${ message.id } </td>
			<td> ${ message.sender.firstname } ${ message.sender.surname }</td>
			<td> ${ message.receiver.firstname }  ${ message.receiver.surname }</td>
			<td> ${ message.text }</td>
			<td> ${ message.isRead } </td>
		</tr>
	</table>
</body>
</html>


