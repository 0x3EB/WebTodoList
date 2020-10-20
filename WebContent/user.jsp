<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tableau de bord</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	</head>

	<body>
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Description</th>
				</tr>
				<c:forEach var="todo" items="${TODO_LIST }">
					<tr>
						<td>${todo.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>



