<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Pitang - Sefaz</title>

</head>
<body>

    <jsp:include page="menuLogado.jsp"></jsp:include>


	<div id="app" class="container mt-5">

	<span id="error">
	</span>	
	
		
<!-- action=UsuarioWS method="post" -->
		<form  id="validaForm">
		
			<div class="form-group">
				<label for="nome">Nome</label> 
				<input type="text" autocomplete="off" class="form-control" id="nome" name="nome" isRequired placeholder="Informe o nome do Usuário">
			</div>
			<div class="form-group">
				<label for="email">Email</label> 
				<input type="email" autocomplete="off" class="form-control" id="email" name="email" isRequired aria-describedby="emailHelp" placeholder="Informe o email">
			</div>
			<div class="form-group">
				<label for="senha">Senha</label> 
				<input type="password" autocomplete="off" class="form-control" id="senha" name="senha" isRequired placeholder="Informe a senha">
			</div>

			<div class="form-group">
				<label for="fixo">Fone Fixo</label> 
				<input type="text" autocomplete="off" class="form-control fixo" id="telefoneFixo" name="telefoneFixo" isRequired placeholder="apenas números">
			</div>

			<div class="form-group">
				<label for="celular">Fone Celular</label> 
				<input type="text" autocomplete="off" class="form-control celular" id="telefoneCelular" name="telefoneCelular" isRequired 	placeholder="apenas números">
			</div>

			<a href="#" id="cadUsuario"  class="btn btn-primary" >Cadastrar</a>
		</form>

	</div>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>