package br.com.pitang.sefaz.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.pitang.sefaz.dao.UsuarioDao;
import br.com.pitang.sefaz.model.Usuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioService {

	@Inject
	private UsuarioDao dao;

	public List<Usuario> listaUsuarios() {
		return dao.getUsuarios();
	}
	
	public Usuario getUsuario(Long id) {
		return dao.getUsuarioPorId(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(@Valid Usuario usuario) throws Exception {

		if (!dao.listUsuarioPorEmail(usuario.getEmail()).isEmpty()) {
			throw new Exception("Email já cadastrado.");
		}

		dao.salvar(usuario);
	}
	
	public void alterar(@Valid Usuario usuario) throws Exception {
		
		boolean emailExistente = dao.listUsuarioPorEmail(usuario.getEmail()).stream().anyMatch(x -> 
					{ return (x.getId() != usuario.getId()); }
				);
		
		if (emailExistente) {
			throw new Exception("Email já cadastrado para outro usuario.");
		}
		dao.atualizar(usuario);
	}
	
	public void deletar(String email) {
		Usuario usuario = dao.listUsuarioPorEmail(email).get(0);
		dao.deletar(usuario);
	}
	
	public Usuario validaLogin(String email, String senha) {
		if(!dao.listUsuarioPorEmail(email.trim()).isEmpty()) {
			Usuario usuario= dao.listUsuarioPorEmail(email.trim()).get(0);
			if(usuario.getSenha().equals(senha))
				return usuario;			
		}
			
		return null;
	}

}
