package br.com.pitang.sefaz;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.pitang.sefaz.exception.MensagemErroDto;
import br.com.pitang.sefaz.exception.MsgException;

@Provider
public class ProvideMsgException implements ExceptionMapper<MsgException> {

	@Override
	public Response toResponse(MsgException exception) {

		List<String> mensagens = new ArrayList<>();
		mensagens.add(exception.getMensagem());
		return Response.status(Response.Status.BAD_REQUEST).entity(MensagemErroDto.build(mensagens)).build();
	}
}
