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
</head>
<body>
	<p class="login">LOGGED USER: ${user.firstname} ${user.surname}, ROLE: ${user.role}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/${user.role}/">Return</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/edit">Edit profile</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/messages">My messages</a>
	</button>
	<div class="${ (user.id == userOfId.id) ? 'hide' : 'show' }">
		<p class="prime">Send message to ${ userOfId.firstname } ${ userOfId.surname }</p>
		<form:form method="post" modelAttribute="messageDto" action="${pageContext.request.contextPath}/message/">
			<form:textarea path="text" rows="5" cols="40"/>
			<form:errors path="text" cssClass="error"/><br>
			<form:hidden path="receiver.id" value="${ userOfId.id }"/>
			<input type="submit" value="Add"/>
		</form:form>
	  </div>
	  <p class="prime">${ userOfId.difficultyLevel }</p>
</body>
</html>


