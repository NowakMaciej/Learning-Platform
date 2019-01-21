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
	<a class="abortButton returnButton" href="${pageContext.request.contextPath}/user/teacher/">Return</a>
	<p class="primeText">Add new Category:</p>
	<div class="leftMargin">
		<form:form method="post" modelAttribute="categoryDto" action="${pageContext.request.contextPath}/category/">
			<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
			<span class="formSpan">Category name:</span>
			<form:input path="name" />
			<form:errors path="name" cssClass="error" />
			<input class="submitButton addButton" type="submit" value="Add"/>
		</form:form>
	</div>
	<p class="primeText">My Categories [${ categories.size() }]</p>
	<table class="table">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Created</th>
			<th>Last Updated</th>
			<th>Actions</th>
			<th>Exercises</th>
		</tr>
		<c:forEach items="${ categories }" var="category">
			<tr>
				<td>${ category.id }</td>
				<td>${ category.name }</td>
				<td>${ category.created }</td>
				<td>${ category.updated }</td>
				<td>
					<button class="actionButton editButton">
						<a href="${pageContext.request.contextPath}/category/${ category.id }">edit</a>
					</button>
					<button class="actionButton deleteButton">
						<a href="${pageContext.request.contextPath}/category/delete/${ category.id }">delete</a>
					</button>
				</td>
				<td>
					<c:forEach items="${ category.exerciseDtos }" var="exerciseDto">
						<c:out value="${ exerciseDto.text }"/><br>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


