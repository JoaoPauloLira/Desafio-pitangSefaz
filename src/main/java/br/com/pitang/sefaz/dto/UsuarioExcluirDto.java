package br.com.pitang.sefaz.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

import com.google.gson.Gson;

import br.com.pitang.sefaz.exception.MsgException;

public class UsuarioExcluirDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	public UsuarioExcluirDto() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public UsuarioExcluirDto desSerializable(BufferedReader r) throws MsgException {

		StringBuilder json = new StringBuilder();
		String linha;
		BufferedReader reader = r;

		try {
			while ((linha = reader.readLine()) != null) {
				json.append(linha);
			}
		} catch (IOException e) {
			throw new MsgException(e.getMessage());
		}

		UsuarioExcluirDto usuario = new Gson().fromJson(json.toString(), UsuarioExcluirDto.class);

		return usuario;
	}
	
	
}
