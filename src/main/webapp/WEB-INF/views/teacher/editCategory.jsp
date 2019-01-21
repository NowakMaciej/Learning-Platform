<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formArticle</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" rel="stylesheet">
</head>
<body>
	<div class="leftMargin topMargin">
		<form:form method="post" modelAttribute="categoryDto">
			<form:hidden path="id" value="${ category.id }"/>
			<div>
				<span class="formSpan">Category name:</span>
				<form:input path="name" />
				<form:errors path="name" cssClass="error" />
			</div>
			<div>
				<input class="submitButton" type="submit" value="Submit"/>
				<a class="abortButton" href="${pageContext.request.contextPath}/category/">Abort</a>
			</div>
		</form:form>
	</div>
</body>
</html>


