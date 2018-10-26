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
	<form:form method="post" modelAttribute="articleDto">
		title:<form:input path="title"/>
		<form:errors path="title" cssClass="error"/><br>
		content:<form:textarea path="content" rows="10" cols="50"/>
		<form:errors path="content" cssClass="error"/><br>
		Authors:<br>
		<form:select path="author.id" items="${ authors }" itemValue="id" itemLabel="lastName"/>
		<form:errors path="author" cssClass="error"/><br>
		Categories:<br>
		<form:select path="categories" items="${ categories }" itemValue="id" itemLabel="name"/>
		<form:errors path="categories" cssClass="error"/><br>
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>


