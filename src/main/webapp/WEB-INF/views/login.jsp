<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets">

<h:head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</h:head>
<h:body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
	<header class="bg-primary">Sistema de gestión de alumnos</header>
	Login
	<div class="contenedorLogin">
		<div class="panel panel-primary">
			<div class="panel-heading">Iniciar Sesión</div>
			<div class="panel-body">
				<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Usuario</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="username" placeholder="Usuario"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="password" placeholder="Password"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">Loguear</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<h1>HDIV Spring Mvc Showcase</h1>

	<p>These modules follow the "learn by example" school. Be sure to
		"look under the hood" to see how it's done.</p>

	<!-- For the sake of example, use the page form with this set of links: -->

	<h2>
		<c:url value="/attacks/attacks.html" var="url" />
		<a href="${url}">Vulnerable Example Section</a>
	</h2>
	<p>This section is a demonstration of common web application flaws.
		These exercises show how easily can penetration techniques be applied.</p>

	<h2>
		<c:url value="/secure/attacks.html" var="url" />
		<a href="${url}">Vulnerable Example Section resolved by HDIV </a>
	</h2>
	<p>This section is a demonstration of common web application flaws
		but they are resolved by HDIV.</p>

	<h2>
		<c:url value="/authenticated/info.html" var="url" />
		<a href="${url}">Authenticated Section </a>
	</h2>
	<p>Application authenticated zone. Secured with Spring Security.</p>

	<h2>
		<c:url value="/fileupload/fileupload.html" var="url" />
		<a href="${url}">File Upload Example Section </a>
	</h2>
	<p>File Upload example form.</p>
				</form>
			</div>
		</div>
		<div style="text-align:center;">
			${mensaje}
		</div>
	</div>
</h:body>
</html>
