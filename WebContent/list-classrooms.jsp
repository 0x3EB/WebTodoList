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
			data-toggle="modal" data-target="#newClassModal" role="button"
			id="newClass"></a>
		<table data-toggle="table" data-search="true" data-pagination="true"
			data-show_columns="false">

			<thead>
				<tr>
					<th>Name</th>
					<th>Number of students</th>
					<th>Archive</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="classe" items="${CLASSROOMS }">
					<c:choose>
    					<c:when test="${classe.archivage=='false'}">
							<c:url var="EditClass" value="editClassroomController">
								<c:param name="classeId" value="${classe.id}" />
							</c:url>
							<tr>
								<td>${classe.name}</td>
								<td>${classe.getNumberOfStudents()}</td>
								<td>${classe.archivage}</td>
								<td><a href="UserControllerServlet"
									class="btn btn-warning  btn-round fa fa-eye" role="button"
									data-toggle="modal" data-target="#largeModal-${classe.id}"></a> 
									<a href="${ EditClass }" class="btn btn-info btn-round fa fa-edit" role="button"></a>
									<a href="#" class="btn btn-danger  btn-round fa fa-trash" role="button"
										data-toggle="modal" data-target="#archiveModal${classe.id}"></a>
									</td>
							</tr>
							<div class="modal fade" id="archiveModal${classe.id}" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Archiving
												Confirmation</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">Are you sure you you want to
											archive this Class?</div>
										<div class="modal-footer">
											<a class="btn btn-secondary" data-dismiss="modal">Cancel</a> 
												 <form action="ArchiveClassroomController" method="get">
												 	<input type="text" id="classeId" name="classeId" value="${classe.id}" hidden="true">
													<button class="btn btn-success">Yes</button>
												 </form>
										</div>
									</div>
								</div>
							</div>
							<div class="modal fade bd-visu-modal-lg" id="largeModal-${classe.id}"
								tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
								aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Details of
												the class : ${classe.name}</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<div>
												<div class="card card-nav-tabs">
													<div class="card-body ">
														<div class="tab-content text-center">
															<div class="tab-pane" id="profile">
															</div>
															<div class="tab-pane active" id="messages">
																<c:set var="compt"  value="1"/>
																<c:forEach var = "e" items="${classe.eleves}">
																	<c:out value = "${compt}"/>: <c:out value = "${e.lastname}"/> <c:out value = "${e.name}"/><br>
																	<c:set var="compt"  value="${compt+1}"/>
																</c:forEach>
															</div>
														</div>
													</div>
												</div>
											</div>
		
		
		
										</div>
										<div class="modal-footer">
											<a class="btn btn-secondary" data-dismiss="modal">Close</a>
										</div>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	<div class="modal fade" id="newClassModal" tabindex="-1" role="dialog"
		aria-labelledby="newTodoLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalLabel">New Class</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="/WebTodoList/addClassControllerServlet" method="post">
						<div class="form-group">
							<label for="message-text" class="col-form-label">Class name:</label>
							<textarea class="form-control" id="classname"
								name="classname"></textarea>
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