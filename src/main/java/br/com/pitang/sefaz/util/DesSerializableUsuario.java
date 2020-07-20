package br.com.pitang.sefaz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import br.com.pitang.sefaz.exception.MsgException;
import br.com.pitang.sefaz.model.Telefone;
import br.com.pitang.sefaz.model.Usuario;
import br.com.pitang.sefaz.model.enums.Tipo;

@Stateless
public class DesSerializableUsuario {

	public DesSerializableUsuario() {
	}
	
	public Usuario getUsuario(Map<String, String[]> map) throws Exception {

		Usuario usuario = new Usuario();
		BeanUtils.populate(usuario, map);

		String telefoneFixo = map.get("telefoneFixo")[0];
		String telefoneCelular = map.get("telefoneCelular")[0];
		
		usuario.getTelefones().add(telefoneBuilder(telefoneFixo.replace("(", "").replace(")","").replace("-", "").replace(".", ""), Tipo.TELEFONE_FIXO));
		usuario.getTelefones().add(telefoneBuilder(telefoneCelular.replace("(", "").replace(")","").replace("-", "").replace(".", ""), Tipo.CELULAR));

		return usuario;
	}

	public Usuario getUsuario(BufferedReader r) throws MsgException {

		StringBuilder json = getJson(r);

		Usuario usuario = new Gson().fromJson(json.toString(), Usuario.class);

		return usuario;
	}
	
	public Long getId(BufferedReader r) throws MsgException {

		StringBuilder json = getJson(r);

		UsuarioAux usuario = new Gson().fromJson(json.toString(), UsuarioAux.class);

		return usuario.getId();
	}
	
	private Telefone telefoneBuilder(String fone, Tipo tipo) {
		Telefone telefone = new Telefone();

			telefone.setDdd(Integer.parseInt(fone.substring(0,2)));
			telefone.setNumero(fone.substring(2));
			telefone.setTipo(tipo);			
		
		return telefone;
	}

	private StringBuilder getJson(BufferedReader r) throws MsgException {
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
		return json;
	}
	
	private class UsuarioAux {
		private Long id;
		public Long getId() { return id; }
		public void setId(Long id) { this.id = id; }
	}
	
}
