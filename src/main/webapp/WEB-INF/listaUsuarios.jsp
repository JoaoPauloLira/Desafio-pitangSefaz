<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
	
	<span id="error">
	</span>	

	<div id="app" class="container mt-5">

		<table id="usuarios" class="table mt-5">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Email</th>
					<th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="lista" items="${listaUsuarios}">
					<tr>
						<th scope="row" hidden="hidden"></th>
						<td>${lista.nome}</td>
						<td>${lista.email}</td>
						<td>
							<div class="btn-group" role="group" aria-label="Basic example">
								
								<c:if test="${!empty usuarioSessao}">
									<a href="#" onclick="editarUsuario(${lista.id})" id="editarUsuario"  class="btn btn-secondary" >Editar</a>
									<a href="#" onclick="excluirUsuario(${lista.id})"  class="btn btn-danger">Excluir</a>
								</c:if>
								<c:if test="${empty usuarioSessao}">
									<a href="#"  class="btn btn-secondary disabled" >Editar</a>
									<a href="#"  class="btn btn-danger disabled">Excluir</a>
								</c:if>								
								
							</div>
						</td>
						
					</tr>
				</c:forEach>


			</tbody>
		</table>
		
		<hr>
		<div class="col align-self-end">
			<small>
				<strong>
					Para Editar e excluir é precisa ter realizado o login!		
				</strong>
			</small> 
		</div>
		
	</div>
	
	<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>