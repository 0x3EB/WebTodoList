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
		<div class="row">
			<div class="col-lg-4 col-md-6 ml-auto mr-auto">
				<form class="form" action="EditTodoControllerServlet" method="post">
					<div class="row">
						<div class="card-body">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class="material-icons">face</i>
									</span>
								</div>
								<input type="text" class="form-control"
									value="${todo.description}" name="description" required
									autofocus>
							</div>
						</div>
						<div class="footer text-center">
							<a class="btn btn-dark btn-round btn-wd btn-lg"
								href="UserControllerServlet">Return to ToDos</a>
						</div>
						<div class="footer text-center">
							<button class="btn btn-success btn-round btn-wd btn-lg"
								type="submit">Update ToDo</button>
						</div>
					</div>
				</form>
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