<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learning Platform</title>
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
</head>
<body>
	<p class="login">LOGGED USER: ${teacher.user.firstname} ${teacher.user.surname},
		ROLE: ${teacher.user.role}</p>
	<button>
		<a href="${pageContext.request.contextPath}/user/teacher/">Return</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/logout">Logout</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/edit">Edit
			profile</a>
	</button>
	<button>
		<a href="${pageContext.request.contextPath}/user/messages">My
			messages</a>
	</button>
	<p class="prime">Add new Category:</p>
	<form:form method="post" modelAttribute="categoryDto" action="${pageContext.request.contextPath}/category/">
		<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
		<form:input path="name" />
		<form:errors path="name" cssClass="error" />
		<br>
		<input type="submit" value="Add" />
	</form:form>
	<p class="prime">My Categories [${ categories.size() }]</p>
	<table>
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
					<button>
						<a href="${pageContext.request.contextPath}/category/${ category.id }">edit</a>
					</button>
					<button>
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


