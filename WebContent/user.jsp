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
					<a href="UserControllerServlet" class="btn btn-success alignright fa fa-plus"
						role="button"></a>
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







