<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formArticle</title>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<button>
		<a href="${pageContext.request.contextPath}/user/teacher/">Abort</a>
	</button>
	<form:form method="post" modelAttribute="categoryDto">
		<form:hidden path="id" value="${ category.id }"/>
		<form:input path="name" />
		<form:errors path="name" cssClass="error" /><br>
		<input type="submit" value="Post" />
	</form:form>
</body>
</html>


