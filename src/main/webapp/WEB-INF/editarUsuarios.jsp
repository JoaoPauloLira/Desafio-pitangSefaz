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
	

		<form  id="validaForm">
			<input type="hidden" id="idUsuario" value="${usuario.id}" name="idUsuario" >
			
			<div class="form-group">
				<label for="nome">Nome</label> 
				<input type="text" autocomplete="off" class="form-control" id="nome" value="${usuario.nome}" name="nome" isRequired placeholder="Informe o nome do Usuário">
			</div>
			<div class="form-group">
				<label for="email">Email</label> 
				<input type="email" autocomplete="off" class="form-control" id="email" value="${usuario.email}" name="email" isRequired aria-describedby="emailHelp" placeholder="Informe o email">
			</div>
			<div class="form-group">
				<label for="senha">Senha</label> 
				<input type="password" autocomplete="off" class="form-control" id="senha" value="${usuario.senha}" name="senha" isRequired placeholder="Informe a senha">
			</div>
			
			<c:forEach var="tele" items="${usuario.telefones}">
				<div class="form-group">
					<c:if test="${tele.tipo == 'TELEFONE_FIXO' }">
						<label for="fixo">Fone Fixo</label> 
						<input type="text" autocomplete="off" class="form-control fixo" id="telefoneFixo" value="${tele.ddd} ${tele.numero}" name="telefoneFixo" isRequired placeholder="apenas números">
					</c:if>  
					<c:if test="${tele.tipo == 'CELULAR' }">
						<label for="celular">Fone Celular</label> 
						<input type="text" autocomplete="off" class="form-control celular" id="telefoneCelular"  value="${tele.ddd} ${tele.numero}" name="telefoneCelular" isRequired 	placeholder="apenas números">
					</c:if> 
				</div>			
			
			</c:forEach>


			<a href="#" id="editUsuario"  class="btn btn-primary" >Editar</a>
		</form>

	</div>

	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>