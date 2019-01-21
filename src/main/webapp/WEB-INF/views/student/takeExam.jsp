<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Take exam</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css" rel="stylesheet">
</head>
<body>
	<div id="loggedUser">
		LOGGED USER: ${student.user.firstname} ${student.user.surname}, ROLE: ${student.user.role}
	</div>
	<p class="primeText">Exam: ${ studentExamDto.examDto.title } [${ studentExamDto.id }]</p>
	<div class="leftMargin container">
		<form:form method="post" modelAttribute="studentExamDto" action="${pageContext.request.contextPath}/exam/take/${ studentExamDto.examDto.id }">
			<form:hidden path="examDto.id" value="${ studentExamDto.examDto.id }"/>
			<table class="table">
				<tr>
					<th>Question</th>
					<th>Answer</th>
				</tr>
				<c:forEach items="${ studentExamDto.examDto.exerciseDtos }" var="exercise" varStatus="status">
					<tr>
						<td>${ exercise.text }</td>
						<td>
							<form:hidden path="examDto.exerciseDtos[${ status.index }].id" value="${ exercise.id }"/>
							<form:input path="examDto.exerciseDtos[${ status.index }].answer" value="?"/>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input class="submitButton" type="submit" value="Submit"/>
		</form:form>
	</div>
</body>
</html>


