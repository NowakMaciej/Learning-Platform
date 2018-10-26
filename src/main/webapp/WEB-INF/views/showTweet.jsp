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
	<p class="login">logged user: ${user.userName}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/">Return</a>
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
	<p class="prime">Tweet</p>
	<table>
		<tr>
			<th>Author</th>
			<th>Content</th>
		</tr>
		<tr>
			<td> ${ tweet.user.userName }</td>
			<td> ${ tweet.text }</td>
		</tr>
	</table>
	<p class="prime">Comments [${ count }]</p>
	<table>
		<tr>
			<th>Author</th>
			<th>Comment</th>
		</tr>
		<c:forEach items="${ comments }" var="comment">
			<tr>
				<td> ${ comment.user.userName } </td>
				<td> ${ comment.text } </td>
			</tr>
		</c:forEach>
	</table><br>
	<p class="prime">Add comment</p>
	<form:form method="post" modelAttribute="commentDto" action="${pageContext.request.contextPath}/comment/${ tweet.id }">
		<form:textarea path="text" rows="5" cols="40"/>
		<form:errors path="text" cssClass="error"/><br>
		<form:hidden path="tweet.id" value="${tweet.id }"/>
		<input type="submit" value="Add"/>
	</form:form>
</body>
</html>


