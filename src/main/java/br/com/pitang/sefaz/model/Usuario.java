package br.com.pitang.sefaz.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import br.com.pitang.sefaz.exception.MsgException;
import br.com.pitang.sefaz.model.enums.Tipo;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Informe o nome do usuário")
	private String nome;

	@NotBlank(message = "Informado o e-mail do Usuário")
	@Email(message = "Informe um email válido")
	private String email;

	@NotBlank(message = "Informe uma senha")
	private String senha;

	@ElementCollection
	private List<Telefone> telefones = new ArrayList<Telefone>();

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha.trim();
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((telefones == null) ? 0 : telefones.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (telefones == null) {
			if (other.telefones != null)
				return false;
		} else if (!telefones.equals(other.telefones))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefones=" + telefones.toString()
				+ "]";
	}

}
