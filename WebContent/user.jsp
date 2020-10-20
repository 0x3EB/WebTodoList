<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*,com.todoapp.web.jdbc.*" %>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tableau de bord</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	</head>
	<% String role = (String)request.getAttribute("role"); %>
	<body>
	<!-- instructor -->
	<% if (role=="instructor"){ %>
		<% List<Class> classes = (List<Class>)request.getAttribute("classes");%>		
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">To do</th>
					<th scope="col">Actions </th>
				</tr>
			</thead> 
			<% for (Class classe:classes){ %>
				<% List<Todo> todos = classe.getTodos(); %> 
				<% for (Todo todo:todos){ %>
					<tr>
						<th scope="col">#</th>
						<td><%= todo.getDescription()  %></td>
						<td>boutons d'actions</td>
					</tr> 
				<%} %>
			<%} %>	
		</table>
	<%} %>
	
	
	<% if (role=="student"){ %>
		<% List<Todo> todos = (List<Todo>)request.getAttribute("todos"); %>
		<!-- student -->
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Instructor</th>
					<th scope="col">To do</th>
				</tr>
			</thead>
			<% for (Todo todo:todos){ %>
				<tr>
					<th scope="col">#</th>
					<td><%= todo.getIdinstructor().getUsername() %></td>
					<td><%= todo.getDescription() %></td>
				</tr> 
			<%} %>
		</table>
	<%} %>
	</body>
</html>



