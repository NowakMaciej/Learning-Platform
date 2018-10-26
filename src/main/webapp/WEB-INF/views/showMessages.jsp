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
	<p class="prime">My messages [${user.firstname} ${user.surname}]</p>
	<p class="prime">Sent</p>
	<table>
		<tr>
			<th>To</th>
			<th>Content</th>
		</tr>
		<c:forEach items="${ sentMessages }" var="sentMessage">
			<tr>
				<td> ${ sentMessage.receiver.firstname }  ${ sentMessage.receiver.surname }</td>
				<td> 
					<a href="${pageContext.request.contextPath}/message/${ sentMessage.id }"> ${ sentMessage.text } </a> 
				</td>
			</tr>
		</c:forEach>
	</table>
	<p class="prime">Received</p>
	<table>
		<tr>
			<th>From</th>
			<th>Content</th>
		</tr>
		<c:forEach items="${ receivedMessages }" var="receivedMessage">
			<tr >
				<td> ${ receivedMessage.sender.firstname }  ${ receivedMessage.sender.surname }</td>
				<td class="${ receivedMessage.isRead ? 'read' : 'readNot' }"> 
					<a href="${pageContext.request.contextPath}/message/${ receivedMessage.id }"> ${ receivedMessage.text } </a> 
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


