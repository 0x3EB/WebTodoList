<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TODO APP | Sign in</title>
<link rel="icon" href="img/appIcon.png">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/login.css">
</head>
<body class="text-center">
	<form class="form-signin" action="/WebTodoList/LoginControllerServlet" method="post">
		<img class="mb-4" src="img/user.png" alt="" width="72" height="72">
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="inputEmail" class="sr-only">Email address</label>
		<input type="email" id="inputEmail" class="form-control"
			placeholder="Email address or username" name="usernameOrEmail" required autofocus> <label
			for="inputPassword" class="sr-only">Password</label> 
		<input
			type="password" id="inputPassword" class="form-control"
			placeholder="Password" name="password" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
		<p class="mt-5 mb-3 text-muted">&copy; KUISSI-LEJEUNE</p>
	</form>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javscript" src="js/bootstrap.min.js"></script>
</body>
</html>