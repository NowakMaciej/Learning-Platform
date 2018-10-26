<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zadania domowe</title>
<link type="text/css" href="/SpringCMS/resources/css/style.css" rel="stylesheet">
</head>
<body>
	<div>
		<button>
			<a href="/SpringCMS/home">Home</a>
		</button>
	</div>
	<br>
	<div>
		<button>
			<a href="/SpringCMS/authors/add">Add Author</a>
		</button>
	</div>
	<br>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Articles</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${ authors }" var="author">
			<tr>
				<td> ${ author.id }</td>
				<td> ${ author.firstName } ${ author.lastName }</td>
				<td>
					<button>
						<a href="/SpringCMS/authors/articles/${author.id}">Show articles</a>
					</button>
				</td>
				<td>
					<button>
						<a href="/SpringCMS/authors/delete/${author.id}">Delete</a>
					</button>
					<button>
						<a href="/SpringCMS/authors/edit/${author.id}">Edit</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>


