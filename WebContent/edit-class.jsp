<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1, shrink-to-fit=no"
	name="viewport">
<title>TODO Application</title>
<link rel="icon" href="img/favicon.png">
<link rel="stylesheet" type="text/css"
	href="css/materialdesignicons.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap4-toggle.min.css">

<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/mdi.css" />
<link rel="stylesheet" href="css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-table.min.css">
<link href="css/material-kit.css?v=2.0.7" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body class="profile-page sidebar-collapse">
	<jsp:include page="menu3.jsp" />
	<div class="container">
		<a href="ClassroomControllerServlet"
			class="btn btn-success  btn-round alignright fa fa-plus"
			data-toggle="modal" data-target="#newStudentModal" role="button"
			id="newStudent"></a>
		<div class="row">
			<div class="col-lg-4 col-md-6 ml-auto mr-auto">
				<form class="form" action="editClassroomController" method="post">
					<div class="row">
						<div class="card-body">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class="material-icons">face</i>
									</span>
								</div>
								<input type="text" class="form-control"
									value="${classe.name}" name="name" required
									autofocus>
							</div>
						</div>
						<div class="footer text-center">
							<a class="btn btn-dark btn-round btn-wd btn-lg"
								href="ClassroomControllerServlet">Return to Classes</a>
							<button class="btn btn-success btn-round btn-wd btn-lg"
								type="submit">Update Class name</button>
						</div>
					</div>
				</form>
				<div style="text-align: center">
					<h4>Students</h4>
					<c:set var="compt"  value="1"/>
					<c:forEach var = "e" items="${classe.eleves}">
						<c:out value = "${compt}"/>: <c:out value = "${e.lastname}"/> <c:out value = "${e.name}"/><br>
						<c:set var="compt"  value="${compt+1}"/>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="newStudentModal" tabindex="-1" role="dialog"
		aria-labelledby="newStudentLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalLabel">New Student</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="/WebTodoList/addStudentControllerServlet" method="post">
						<div class="form-group">
							<div>
								<label for="username" class="col-form-label">username:</label>
								<input type="text" class="form-control" id="username"
									name="username" required></input>
							</div>
							<div>
								<label for="name" class="col-form-label">name:</label>
								<input type="text" class="form-control" id="name"
									name="name" required></input>
							</div>
							<div>
								<label for="lastname" class="col-form-label">lastname:</label>
								<input type="text" class="form-control" id="lastname"
									name="lastname" required></input>
							</div>
							<div>
								<label for="mail" class="col-form-label">mail:</label>
								<input type="email" class="form-control" id="mail"
									name="mail" required></input>
							</div>
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