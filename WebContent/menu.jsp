<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark" style="background-color: #303030;">
    <a class="navbar-brand" href="{{ path('home') }}">
        <img src="img/logo-esilv-ecole-ingenieur.png" width="35" height="25" alt="">
    </a>

    <button aria-controls="navbarSite" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler" data-target="#navbarSite" data-toggle="collapse" type="button">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSite">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link fa fa-home active" href="UserControllerServlet">
                    To Do
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link fa fa-th-list active" href="">
                    Classrooms
                </a>
            </li>
        </ul>
        <span class="navbar-text fa fa-user-circle" style="color:white;">
        	${sessionScope.user.username}
			[<i>${sessionScope.user.idrole.libelle}</i>]
			<!-- test
			<i>test</i> -->
        </span>
    </div>
</nav>
<br/>
