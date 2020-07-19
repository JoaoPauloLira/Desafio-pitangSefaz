package br.com.pitang.sefaz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletLogout", urlPatterns = { "/LogoutWS" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			HttpSession sessao = request.getSession();
			
			if (sessao != null) {
				sessao.removeAttribute("usuarioSessao");
				sessao.invalidate();
				System.out.println("sessao morreu");
			}

			response.sendRedirect("login.jsp");

		} catch (Exception e) {
			System.out.println("Erro no logout = " + e.getMessage());
			//response.sendRedirect("error.jsp");
		}
	}

}
