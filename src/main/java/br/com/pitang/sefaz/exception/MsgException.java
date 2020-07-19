package br.com.pitang.sefaz.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class MsgException extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensagem;

	public MsgException() {
		super();
	}

	public MsgException(String mensagem) {

		super(mensagem);
		this.mensagem = mensagem;

	}

	public String getMensagem() {
		return mensagem;
	}
}