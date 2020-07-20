package br.com.pitang.sefaz.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@Stateless
public class ResponseAdpter {

	public ResponseAdpter() {
		
	}

	// private static ResponseAdpter instancia;

//	public static ResponseAdpter getInstancia() {
//
//		if (instancia == null) {
//			instancia = new ResponseAdpter();
//		}
//		return instancia;
//	}

	public void okJson(Object obj, HttpServletResponse resp) throws IOException {
		Gson gson = new Gson();
		String employeeJsonString = gson.toJson(obj);

		resp.setStatus(HttpStatus.OK);

		PrintWriter out = null;
		out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(employeeJsonString);
		out.flush();
	}

	public void ResponseCREATED(HttpServletResponse resp) {
		resp.setStatus(HttpStatus.CREATED);
	}

	public void ResponseDelete(HttpServletResponse resp) {
		resp.setStatus(HttpStatus.OK);
	}

	public void ResponseEditar(HttpServletResponse resp) {
		resp.setStatus(HttpStatus.OK);
	}

	public void ResponseError(HttpServletResponse resp, String error) throws IOException {

		resp.setStatus(HttpStatus.BAD_GATEWAY);

		PrintWriter out = null;
		out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(error);
		out.flush();

	}

}
