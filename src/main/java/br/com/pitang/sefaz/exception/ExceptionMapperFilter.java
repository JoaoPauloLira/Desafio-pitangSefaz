package br.com.pitang.sefaz.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapperFilter implements ExceptionMapper<MsgException> {

	public Response toResponse(MsgException exception) {
		
		 List<String> mensagens = new ArrayList<String>();
		 mensagens.add(exception.getMensagem());
		 return Response
	                .status(Response.Status.BAD_REQUEST)
	                .entity( MensagemErroDto.build(mensagens))
	                .build();
	}

}