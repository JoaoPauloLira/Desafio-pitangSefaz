<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<noscript>
  	<div class="alert alert-warning" role="alert">
  		<strong>
	  		Atenção: para o sistema funcionar perfeitamente, o javascript do browser deve esta ativo!
  		</strong>
	</div>
</noscript>

<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-dark">
		<a class="navbar-brand text-white" href="<c:url value="/" />">CRUD - Usuários</a>

		<div class="btn-group" role="group" aria-label="Basic example">
			<a href="UsuarioWS?logica=ListarUsuarios" class="btn btn-secondary">Listar usuários</a> <a
				href="UsuarioWS?redirect=cadastroUsuarios" class="btn btn-secondary">Cadastrar usuários</a>
		</div>
		 
		| 
		 
				

		<c:if test="${!empty usuarioSessao}">
			<div class="btn-group" role="group" aria-label="Basic example">
				<a class="navbar-brand text-primary" href="#"><h5>Usuário Logado: ${usuarioSessao.email } </h5></a> 
				<a class="navbar-brand text-warning" href="LogoutWS"><h5>Logout</h5></a>
			</div>
		</c:if>
		
		
		<c:if test="${empty usuarioSessao}">
			<a class="navbar-brand text-info" href="LoginWS"><h5>Login</h5></a>
		</c:if>
		
		
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>
</header>