$(document).ready(function () {


	$("#cadUsuario").click(function (e) {

		if (validaForm()) {

			let data = '{' +
				'	"nome": "' + $("#nome").val() + '",					' +
				'	"email": "' + $("#email").val() + '",	' +
				'	"senha": "' + $("#senha").val() + '",						' +
				'	"telefones": [							' +
				'		{									' +
				'			"ddd": ' + getDdd($("#telefoneFixo").val()) + ',						' +
				'			"numero": "' + getNumero($("#telefoneFixo").val()) + '",			' +
				'			"tipo": "TELEFONE_FIXO"			' +
				'		},									' +
				'		{									' +
				'			"ddd": ' + getDdd($("#telefoneCelular").val()) + ',						' +
				'			"numero": "' + getNumero($("#telefoneCelular").val()) + '",			' +
				'			"tipo": "CELULAR"				' +
				'		}									' +
				'	]										' +
				'}'
			chamadaAjaxJson("UsuarioWS", data, listarSucessoCadastro, listarErro, "post")
		}
	});

	$("#editUsuario").click(function (e) {

		if (validaForm()) {

			let data = '{' +
				'	"id": "' + $("#idUsuario").val() + '",					' +
				'	"nome": "' + $("#nome").val() + '",					' +
				'	"email": "' + $("#email").val() + '",	' +
				'	"senha": "' + $("#senha").val() + '",						' +
				'	"telefones": [							' +
				'		{									' +
				'			"ddd": ' + getDdd($("#telefoneFixo").val()) + ',						' +
				'			"numero": "' + getNumero($("#telefoneFixo").val()) + '",			' +
				'			"tipo": "TELEFONE_FIXO"			' +
				'		},									' +
				'		{									' +
				'			"ddd": ' + getDdd($("#telefoneCelular").val()) + ',						' +
				'			"numero": "' + getNumero($("#telefoneCelular").val()) + '",			' +
				'			"tipo": "CELULAR"				' +
				'		}									' +
				'	]										' +
				'}'
			chamadaAjaxJson("UsuarioWS", data, listarSucessoEditar, listarErro, "put")
		}
	});


})

function validaForm() {
	let getInput = $("input[isRequired]")
	let input = getInput.filter(function () {
		return !this.value
	}).get()
	if (input.length) {
		alert("Atenção Todos os campos devem ser preenchidos.")
		return false
	}
	return true
}

function chamadaAjaxJson(servlet, parametros, metodoSucesso, metodoErro, httpMethod) {
	$.ajax({
		url: servlet,
		type: httpMethod,
		data: parametros,
		beforeSend: function () {
			$("#resultado").html("ENVIANDO...");
		}
	})
		.done(function (msg) {
			metodoSucesso(msg);
		})
		.fail(function (jqXHR, textStatus, msg) {
			metodoErro(jqXHR.responseText);
		});
}

function listarSucessoCadastro(sucesso) {
	alert("Usuario cadastrado com Sucesso!");
	let origin = $(location).attr('origin');
	let url = origin + "/SefazJsp/UsuarioWS?logica=ListarUsuarios"
	$(location).attr('href', url);
}

function listarSucessoExcluir(sucesso) {
	alert("Usuario excluido com Sucesso!");
	let origin = $(location).attr('origin');
	let url = origin + "/SefazJsp/UsuarioWS?logica=ListarUsuarios"
	$(location).attr('href', url);
}

function listarSucessoEditar(sucesso) {
	alert("Usuario Editado com Sucesso!");
	let origin = $(location).attr('origin');
	let url = origin + "/SefazJsp/UsuarioWS?logica=ListarUsuarios"
	$(location).attr('href', url);
}

function listarErro(erro) {
	$("#error").html("<div class='alert alert-danger'>" + erro + "</div>");
}

function getDdd(fone) {
	let telefone = fone.replace("(", "").replace(")", "").replace(".", "").replace("-", "").replace(" ", "").trim();
	return telefone.substr(0, 2);
}

function getNumero(fone) {
	let telefone = fone.replace("(", "").replace(")", "").replace(".", "").replace("-", "").replace(" ", "").trim();
	return telefone.substr(2);
}

function excluirUsuario(id) {
	let data = '{"id" : ' + id + '}'
	chamadaAjaxJson("UsuarioWS", data, listarSucessoExcluir, listarErro, "delete")
}