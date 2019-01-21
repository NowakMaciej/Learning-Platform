<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit exam</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css" rel="stylesheet">
</head>
<body>
	<div class="leftMargin topMargin">
		<form:form method="post" modelAttribute="examDto">
			<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
			<div>
				<span class="formSpan">Title</span>
				<form:textarea path="title" rows="2" cols="40"/>
				<form:errors path="title" cssClass="error"/>
			</div>
			<div>
				<span class="formSpan">Difficulty level:</span>
				<form:select class="formSelect" path="difficultyLevel" 
				items="${ difficultyLevels }" itemValue="description" itemLabel="description"/>
			</div>
			<div>
				<span class="formSpan">Exercises:</span>
				<table class="tableNewExam">
				<tr>
					<th>Id</th>
					<th>Question</th>
					<th>Answer</th>
					<th>Difficulty Level</th>
					<th>Categories</th>
					<th>Select</th>
				</tr>
				<c:forEach items="${ exercises }" var="exercise">
					<tr>
						<td>${ exercise.id }</td>
						<td>${ exercise.text }</td>
						<td>${ exercise.answer }</td>
						<td>${ exercise.difficultyLevel }</td>
						<td>
							<c:forEach items="${ exercise.categoryDtos }" var="categoryDto">
								<c:out value="${ categoryDto.name }"/><br>
							</c:forEach>
						</td>
						<td>
							<form:checkbox path="exerciseDtos" value="${ exercise.id }"/>
						</td>
					</tr>
				</c:forEach>
				</table><br>
				<div>
					<input class="submitButton" type="submit" value="Edit" />
					<a class="abortButton" href="${pageContext.request.contextPath}/exam/">Abort</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>


