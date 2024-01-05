<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List All Books</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	crossorigin="anonymous">
</head>
<body>
	<%-- Disable caching of records --%>
	<%
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Expires", "-1");
	response.addHeader("Pragma", "no-cache");
	%>
	<%-- 
<sql:setDataSource var="ds" url="jdbc:oracle:thin:@localhost:1521/XE" user="hr" password="HR"/>

<sql:query var="resultset" sql="select * from books" dataSource="${ds}" ></sql:query>

--%>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #011a87">
			<div>
				<a href="<%=request.getContextPath()%>/list" class="navbar-brand">
					Books Management </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Books</a></li>
			</ul>
		</nav>
	</header>

	<div class="row">

		<div class="container">
			<h3 class="text-center">List of Books</h3>
			<hr>

			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success"
					style="background-color: #fec756">Add New Book</a>

			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Author</th>
						<th>Price</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="row" items="${bookslist}">

						<tr>
							<td><c:out value="${row.id}"></c:out></td>
							<td><c:out value="${row.title}"></c:out></td>
							<td><c:out value="${row.author}"></c:out></td>
							<td><c:out value="${row.price}"></c:out></td>
							<td><c:out value="${row.quantity}"></c:out></td>
							<td><a href="edit?id=<c:out value='${row.id}'/>">Edit</a></td>
							<td><a href="delete?id=<c:out value='${row.id}'/>">Delete</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>


		</div>


	</div>


</body>
</html>