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
	
	public Usuario desSerializable(Map<String, String[]> map) throws Exception {

		Usuario usuario = new Usuario();
		BeanUtils.populate(usuario, map);

		String telefoneFixo = map.get("telefoneFixo")[0];
		String telefoneCelular = map.get("telefoneCelular")[0];
		
		usuario.getTelefones().add(new Telefone().builder(telefoneFixo.replace("(", "").replace(")","").replace("-", "").replace(".", ""), Tipo.TELEFONE_FIXO));
		usuario.getTelefones().add(new Telefone().builder(telefoneCelular.replace("(", "").replace(")","").replace("-", "").replace(".", ""), Tipo.CELULAR));

		return usuario;
	}

	public Usuario desSerializable(BufferedReader r) throws MsgException {

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

		Usuario usuario = new Gson().fromJson(json.toString(), Usuario.class);

		return usuario;
	}
	
}
