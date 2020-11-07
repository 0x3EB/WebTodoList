<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TODO APP | Sign in</title>
<link rel="icon" href="img/favicon.png">
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
</head>


<body class="login-page sidebar-collapse">
	<div class="page-header header-filter">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 ml-auto mr-auto">
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger" role="alert">${errorMessage}</div>
					</c:if>
					<div class="card card-login">
						<form class="form" action="/WebTodoList/LoginControllerServlet"
							method="post">
							<div class="card-header card-header text-center">
								<img src="img/logo-esilv-ecole-ingenieur.png" />
								<h4 class="card-title">ToDo Web App</h4>
							</div>
							<p class="description text-center">Fill informations to
								login..</p>
							<div class="card-body">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">face</i>
										</span>
									</div>
									<input value="${displayUsername}" type="text"
										class="form-control" placeholder="Username / Email..."
										name="usernameOrEmail" required autofocus>
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="material-icons">lock_outline</i>
										</span>
									</div>
									<input type="password" class="form-control"
										placeholder="Password..." name="password" required>
								</div>
							</div>
							<div class="footer text-center">
								<button class="btn btn-rose btn-round btn-wd btn-lg"
									type="submit">Login</button>
							</div>
						</form>
					</div>
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