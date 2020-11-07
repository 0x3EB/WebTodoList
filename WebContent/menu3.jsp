<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<nav class="navbar navbar-inverse navbar-expand-lg bg-dark"
	color-on-scroll="100" id="sectionsNav">
	<div class="container">
		<div class="navbar-translate">
			<a class="navbar-brand" href="UserControllerServlet"> ToDo
				Dynamic web App</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="sr-only">ToDo Dynamic web APP</span> <span
					class="navbar-toggler-icon"></span> <span
					class="navbar-toggler-icon"></span> <span
					class="navbar-toggler-icon"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">
				<li class="dropdown nav-item"><a href="#"
					class="dropdown-toggle nav-link" data-toggle="dropdown"> <i
						class="material-icons">apps</i> ToDo
				</a>
					<div class="dropdown-menu dropdown-with-icons">
						<a href="UserControllerServlet" class="dropdown-item"> <i class="material-icons">filter_list</i>Display
							ToDos undone
						</a> <a href="DoneTodoControllerServlet" class="dropdown-item"> <i class="material-icons">content_paste</i>Display
							 ToDos done
						</a>
					</div></li>
				<c:if
					test="${fn:toUpperCase(sessionScope.user.idrole.libelle) == 'INSTRUCTOR'}">
					<li class="nav-item"><a class="nav-link"
						href="ClassroomControllerServlet"> <i class="material-icons">school</i>
							Classrooms
					</a></li>
				</c:if>
				<li class="nav-item"><a href="javascript:;"
					class="btn btn-info btn-raised btn-fab btn-round"
					data-toggle="dropdown"> <i class="material-icons">email</i>
				</a></li>
				<li class="dropdown nav-item"><a href="javascript:;"
					class="profile-photo dropdown-toggle nav-link"
					data-toggle="dropdown">
						<div class="profile-photo-small">
							<img src="img/user2.jpg" alt="Circle Image"
								class="rounded-circle img-fluid">
						</div>
				</a>
					<div class="dropdown-menu dropdown-menu-right">
						<h6 class="dropdown-header">My account</h6>
						<a class="dropdown-item">${sessionScope.user.name}
							${sessionScope.user.lastname}</a> <a href="SettingsControllerServlet"
							class="dropdown-item">Settings</a> <a
							href="LogoutControllerServlet" class="dropdown-item">Sign out</a>
					</div></li>
			</ul>
		</div>
	</div>
</nav>