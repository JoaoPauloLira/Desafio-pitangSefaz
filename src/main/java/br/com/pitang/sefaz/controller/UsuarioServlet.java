package br.com.pitang.sefaz.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pitang.sefaz.dto.UsuarioExcluirDto;
import br.com.pitang.sefaz.model.Usuario;
import br.com.pitang.sefaz.service.UsuarioService;
import br.com.pitang.sefaz.util.DesSerializableUsuario;
import br.com.pitang.sefaz.util.ResponseAdpter;

@WebServlet(name = "Servlet", urlPatterns = { "/UsuarioWS" })
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private DesSerializableUsuario desSerializableUsuario;

	@Inject
	private ResponseAdpter responseAdpter;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logica = request.getParameter("logica");
		String redirect = request.getParameter("redirect");
		String editar = request.getParameter("editar");

		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioSessao");

		if (logica != null) {
			List<Usuario> usuarios = usuarioService.listaUsuarios();
			request.setAttribute("listaUsuarios", usuarios);
			request.getRequestDispatcher("/WEB-INF/listaUsuarios.jsp").forward(request, response);

		} else if (redirect != null) {
			request.getRequestDispatcher("/WEB-INF/" + request.getParameter("redirect") + ".jsp").forward(request,
					response);
		} else if (editar != null) {
			if (usuarioLogado == null) {
				responseAdpter.ResponseError(response, "Para excluir é precisa esta logado!");
			} else {

				Long id = Long.parseLong(request.getParameter("editar"));
				Usuario usuarios = usuarioService.getUsuario(id);
				request.setAttribute("usuario", usuarios);
				request.getRequestDispatcher("/WEB-INF/editarUsuarios.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Usuario usuario;
			usuario = desSerializableUsuario.desSerializable(request.getReader());
			usuarioService.salvar(usuario);

			responseAdpter.ResponseCREATED(response);
		} catch (Exception e) {
			e.printStackTrace();
			responseAdpter.ResponseError(response, e.getMessage());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioSessao");

		if (usuarioLogado == null) {
			responseAdpter.ResponseError(resp, "Para excluir é precisa esta logado!");
			resp.sendRedirect("login.jsp");
		} else {
			try {
				UsuarioExcluirDto usuario;
				usuario = new UsuarioExcluirDto().desSerializable(req.getReader());

				if (usuario.getId() == usuarioLogado.getId()) {
					responseAdpter.ResponseError(resp, "Não é permitido excluir o usuario que esta logado no momento!");
				} else {
					Usuario usuarioDelete = usuarioService.getUsuario(usuario.getId());
					usuarioService.deletar(usuarioDelete.getEmail());
					responseAdpter.ResponseDelete(resp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				responseAdpter.ResponseError(resp, "Erro ao tentar excluir o Usuário");
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioSessao");

		if (usuarioLogado == null) {
			responseAdpter.ResponseError(resp, "Para editar é precisa esta logado!");
		} else {
			try {
				Usuario usuario;
				usuario = desSerializableUsuario.desSerializable(req.getReader());
				usuarioService.alterar(usuario);
				System.out.println("editar");

				responseAdpter.ResponseEditar(resp);
			} catch (Exception e) {
				e.printStackTrace();
				responseAdpter.ResponseError(resp, e.getMessage());
			}
		}

	}

}
