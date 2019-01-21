<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit exercise</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css" rel="stylesheet">
</head>
<body>
	<div class="leftMargin topMargin">
		<form:form method="post" modelAttribute="exerciseDto">
			<form:hidden path="id" value="${ exercise.id }"/>
			<form:hidden path="teacherDto.id" value="${ teacher.id }"/>
			<div>
				<span class="formSpan">Question</span>
				<form:textarea path="text"/>
				<form:errors path="text" cssClass="error" />
			</div>
			<div>
				<span class="formSpan">Answer:</span>
				<form:textarea path="answer" />
				<form:errors path="answer" cssClass="error" />
			</div>
			<div>
				<span class="formSpan">Difficulty level:</span>
				<form:select class="formSelect" path="difficultyLevel" items="${ difficultyLevels }" itemValue="description" itemLabel="description"/>
			</div>
			<div>
				<span class="formSpan">Categories:</span>
				<form:select class="formSelect" path="categoryDtos" items="${ categories }" itemValue="id" itemLabel="name"/>
			</div>
			<div>
				<input class="submitButton" type="submit" value="Edit" />
				<a class="abortButton" href="${pageContext.request.contextPath}/exercise/">Abort</a>
			</div>
		</form:form>
	</div>
</body>
</html>


