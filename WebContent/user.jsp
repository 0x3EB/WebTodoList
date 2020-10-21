<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Tableau de bord</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>

	<body>
	
	<jsp:include page="menu.jsp" />
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th>#</th>
							<th>Description</th>
						</tr>
					</thead>
					<c:forEach var="todo" items="${TODO_LIST }">
						<tr>
							<td></td>
							<td>${todo.description}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
	
	
	</body>
</html>



