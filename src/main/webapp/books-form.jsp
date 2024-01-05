<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books Management Form</title>
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
				<a href="<%=request.getContextPath()%>" class="navbar-brand">
					Books Management </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>" class="nav-link">Books</a></li>
			</ul>
		</nav>
	</header>

	<div class="container col-md-5">

		<div class="card">
			<div class="card-body">

				<c:if test="${book !=null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${book ==null }">
					<form action="add" method="post">
				</c:if>
				<caption>
					<c:if test="${book==null }">
						<h2>Add Book</h2>
					</c:if>
					<c:if test="${book!=null }">
						<h2>Edit Details</h2>
					</c:if>

				</caption>


				<c:if test="${book != null}">
					<input type="hidden" name="id" value="<c:out value='${book.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Book Name</label> <input type="text"
						value="<c:out value='${book.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Author</label> <input type="text"
						value="<c:out value='${book.author}' />" class="form-control"
						name="author" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Quantity</label> <input type="text"
						value="<c:out value='${book.quantity}' />" class="form-control"
						name="quantity" required="required">
				</fieldset>


				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out value='${book.price}' />" class="form-control"
						name="price" required="required">
				</fieldset>



				<button type="submit" class="btn btn-success">Save</button>

			</div>
		</div>
	</div>
</body>