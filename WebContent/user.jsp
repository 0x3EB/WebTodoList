<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1, shrink-to-fit=no"
	name="viewport">
<title>TODO Application</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/materialdesignicons.min.css">
<link rel="stylesheet" type="text/css"
	href="css/boostrap4-toggle.min.css">
<link rel="stylesheet" type="text/css" href="css/boostrap-table.css">
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/boostrap-table.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container">

		<div class="jumbotron">
			<div class="row">

				<div class="col-12">
					<a href="UserControllerServlet"
						class="btn btn-success alignright fa fa-plus" data-toggle="modal"
						data-target="#exampleModal" role="button"></a>
					<table class="table table-striped table-bordered table-sm"
						data-toggle="table" data-search="true" data-pagination="true">
						<thead>
							<tr>
								<th>id</th>
								<th>Description</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="todo" items="${TODO_LIST }">
								<tr>
									<td>${todo.id}</td>
									<td>${todo.description}</td>
									<td><a href="UserControllerServlet"
										class="btn btn-primary fa fa-eye" role="button"></a> <a
										href="#" class="btn btn-info fa fa-edit" role="button"></a> <a
										href="#" class="btn btn-danger fa fa-trash" role="button"></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
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
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Class:</label>
							<select class="form-control"
								id="class" name="class">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Description:</label>
							<textarea class="form-control" id="description"
								name="description"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Add</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/select2.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
	<script type="text/javascript" src="js/boostrap-table.js"></script>
	<script type="text/javascript" src="js/boostrap-table.min.js"></script>
	<script type="text/javascript" src="js/boostrap-table-fr-FR.js"></script>
</body>
</html>







