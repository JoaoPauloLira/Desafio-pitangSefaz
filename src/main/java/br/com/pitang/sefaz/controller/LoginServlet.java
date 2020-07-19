package br.com.pitang.sefaz.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.pitang.sefaz.model.Usuario;
import br.com.pitang.sefaz.service.UsuarioService;

@WebServlet(name = "ServletLogin", urlPatterns = { "/LoginWS" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuarioLogin = usuarioService.validaLogin(request.getParameter("email"),
				request.getParameter("password"));

		HttpSession sessao = request.getSession();
		if (sessao != null) {
			sessao.removeAttribute("usuarioSessao");
			sessao.invalidate();
			System.out.println("sessao morreu");
		}

		if (usuarioLogin != null) {
			sessao = request.getSession(true);
			sessao.setAttribute("usuarioSessao", usuarioLogin);

			System.out.println("Usuario logado = " + usuarioLogin.getEmail());

			response.sendRedirect("UsuarioWS?logica=ListarUsuarios");
		}

		request.setAttribute("error", "Usuario não cadastrado");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
