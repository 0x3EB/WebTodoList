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
					<th></th>
					<th>Instructor</th>
					<th>Description</th>
					<th>Classroom</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="todo" items="${TODO_LIST }">
					<tr>
						<td><a href="../index.html"> <i class="material-icons">done_all</i>
						</a></td>
						<td>${todo.idinstructor.username}</td>
						<td>${todo.description}</td>
						<td>${todo.description}</td>
						<td><a href="UserControllerServlet"
							class="btn btn-warning  btn-round fa fa-eye" role="button"
							data-toggle="modal" data-target="#largeModal-${todo.id}"></a> <a
							href="#" class="btn btn-info btn-round fa fa-edit" role="button"></a>
							<a href="#" class="btn btn-danger  btn-round fa fa-trash"
							role="button" data-toggle="modal" data-target="#deleteModal"></a></td>
					</tr>
					<div class="modal fade" id="deleteModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Deleting
										Confirmation</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">Are you sure you you want to
									delete this ToDo?</div>
								<div class="modal-footer">
									<a class="btn btn-secondary" data-dismiss="modal">Cancel</a> <a
										class="btn btn-success" href="#">Yes</a>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade bd-visu-modal-lg" id="largeModal-${todo.id}"
						tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Details of
										the ToDo n° ${todo.id}</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body"></div>
								<div class="modal-footer">
									<a class="btn btn-secondary" data-dismiss="modal">Close</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="modal fade" id="newTodoModal" tabindex="-1" role="dialog"
		aria-labelledby="newTodoLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New ToDo</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="/WebTodoList/addTodoControllerServlet" method="post">
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Class:</label>
							<select class="form-control" id="class" name="class">
								<c:forEach var="classroom" items="${CLASSROOMS_LIST}">
									<option>${classroom.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Description:</label>
							<textarea class="form-control" id="description"
								name="description"></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-success">Add</button>
						</div>
					</form>
				</div>
			</div>
		</div>
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







