<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Take exam</title>
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
</head>
<body>
	<p class="login">LOGGED USER: ${student.user.firstname} ${student.user.surname},
		ROLE: ${student.user.role}</p>
	<p class="prime">${ exam.title }</p>
	<form:form method="post" modelAttribute="examDto" action="${pageContext.request.contextPath}/exam/take/${ examDto.id }">
			<table>
			<tr>
				<th>Question</th>
				<th>Answer</th>
			</tr>
			<c:forEach items="${ examDto.exerciseDtos }" var="exercise" varStatus="status">
				<tr>
					<td>${ exercise.text }</td>
					<td>
						<form:hidden path="exerciseDtos[${ status.index }].id" value="${ exercise.id }"/>
						<form:input path="exerciseDtos[${ status.index }].answer" value="?"/>
					</td>
				</tr>
			</c:forEach>
			</table><br>
			<input type="submit" value="Submit" />
	</form:form>
	${ examDto }
</body>
</html>


