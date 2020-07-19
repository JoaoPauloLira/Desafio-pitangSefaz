<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="menu.jsp"></jsp:include>
	
	<br/>
	<c:if test="${!empty error}">
		<div class='alert alert-danger'>${error}</div>
	</c:if>

<div class="container">
		<h1>Login</h1>
		<form action="LoginWS" method="post">
			<input type="hidden" name="logica" value="login">
			<div class="form-group">
				<label>E-mail</label> <input type="text" name="email"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Senha</label> <input type="password" name="password"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Logar</button>
		</form>
	</div>
</body>
</html>