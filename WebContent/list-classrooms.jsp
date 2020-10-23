<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1, shrink-to-fit=no"
	name="viewport">
<title>TODO Application</title>
<link rel="stylesheet" type="text/css"
	href="css/materialdesignicons.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap4-toggle.min.css">

<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-table.min.css">
<link href="css/material-kit.css?v=2.0.7" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body class="profile-page sidebar-collapse">
	<jsp:include page="menu3.jsp" />
	<div class="container">
		<a href="UserControllerServlet"
			class="btn btn-success  btn-round alignright fa fa-plus"
			data-toggle="modal" data-target="#newTodoModal" role="button"
			id="newTodo"></a>
		<table data-toggle="table" data-search="true" data-pagination="true"
			data-show_columns="false">

			<thead>
				<tr>
					<th>Name</th>
					<th>Number of students</th>
					<th>Active</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="class" items="${CLASSROOM_LIST }">
					<tr>
						<td>${class.name}</td>
						<td>${class.getNumberOfStudents}</td>
						<td>True</td>
						<td><a href="UserControllerServlet"
							class="btn btn-warning  btn-round fa fa-eye" role="button"
							data-toggle="modal" data-target="#largeModal-${todo.id}"></a> <a
							href="#" class="btn btn-info btn-round fa fa-edit" role="button"></a>
							<a href="#" class="btn btn-danger  btn-round fa fa-trash"
							role="button" data-toggle="modal" data-target="#deleteModal"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/select2.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
	<script src="js/bootstrap-material-design.min.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="js/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/bootstrap-table.min.js"></script>
	<script src="js/material-kit.js?v=2.0.7" type="text/javascript"></script>
</body>
</html>







