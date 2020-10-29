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
		<form action="" method="post">
			<div class="form-group">
				<label for="message-text" class="col-form-label">Role:</label> <input
					type="text" class="form-control" id="description"
					name="description" value="${sessionScope.user.idrole.libelle }"
					required readonly>
			</div>
			<div class="form-group">
				<label for="message-text" class="col-form-label">Username:</label> <input
					type="text" class="form-control" id="description"
					name="description" value="${sessionScope.user.username }" required>
			</div>
			<div class="form-group">
				<label for="message-text" class="col-form-label">Name:</label> <input
					type="text" class="form-control" id="description"
					name="description" value="${sessionScope.user.name }" readonly>
			</div>
			<div class="form-group">
				<label for="message-text" class="col-form-label">Lastname:</label> <input
					type="text" class="form-control" id="description"
					name="description" value="${sessionScope.user.lastname }" readonly>
			</div>
			<div class="form-group">
				<label for="message-text" class="col-form-label">E-Mail:</label> <input
					type="text" class="form-control" id="description"
					name="description" value="${sessionScope.user.email }" required>
			</div>
			<div class="form-group">
				<label for="message-text" class="col-form-label">Password:</label> <input
					class="form-control" id="description" type="password"
					name="description" value="${sessionScope.user.password}" required>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-success">Update</button>
			</div>
		</form>
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