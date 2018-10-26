<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learning Platform</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<p class="login">logged user: ${user.userName}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/edit">Edit profile</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/messages">My messages</a>
	</button>
	<p class="prime">Post new Tweet:</p>
	<form:form method="post" modelAttribute="tweetDto" action="${pageContext.request.contextPath}/tweet/">
		<form:textarea path="text" rows="5" cols="40"/>
		<form:errors path="text" cssClass="error"/><br>
		<input type="submit" value="Post"/>
	</form:form><br>
	<p class="prime">All Tweets</p>
	<table>
		<tr>
			<th>Id</th>
			<th>Author</th>
			<th>Content</th>
			<th>Created</th>
		</tr>
		<c:forEach items="${ tweets }" var="tweet">
			<tr>
				<td> ${ tweet.id } </td>
				<td> 
					<a href="${pageContext.request.contextPath}/user/${tweet.user.id}"> ${ tweet.user.userName } </a>
				</td>
				<td> 
					<a href="${pageContext.request.contextPath}/tweet/${ tweet.id }"> ${ tweet.text } </a>
				</td>
				<td> ${ tweet.created }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


