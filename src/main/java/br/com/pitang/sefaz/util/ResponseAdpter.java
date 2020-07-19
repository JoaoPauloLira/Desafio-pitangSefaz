package br.com.pitang.sefaz.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResponseAdpter {

	private static Gson gson;

	private ResponseAdpter() {
		gson = new Gson();
	}

	private static ResponseAdpter instancia;

	public static ResponseAdpter getInstancia() {

		if (instancia == null) {
			instancia = new ResponseAdpter();
		}
		return instancia;
	}

	public static void okJson(Object obj, HttpServletResponse resp) throws IOException {
		String employeeJsonString = gson.toJson(obj);

		resp.setStatus(HttpStatus.OK);
		
		PrintWriter out = null;
		out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(employeeJsonString);
		out.flush();
	}
	
	public static void ResponseCREATED(HttpServletResponse resp) {
		resp.setStatus(HttpStatus.CREATED);
	}
	
	public static void ResponseDelete(HttpServletResponse resp) {
		resp.setStatus(HttpStatus.OK);
	}
	
	public static void ResponseEditar(HttpServletResponse resp) {
		resp.setStatus(HttpStatus.OK);
	}
	
	
	public static void ResponseError(HttpServletResponse resp, String error) throws IOException {
		
		resp.setStatus(HttpStatus.BAD_GATEWAY);

		PrintWriter out = null;
		out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(error);
		out.flush();
		
	}

}
