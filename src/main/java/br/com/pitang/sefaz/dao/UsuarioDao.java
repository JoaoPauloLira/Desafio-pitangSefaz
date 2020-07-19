package br.com.pitang.sefaz.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.pitang.sefaz.model.Usuario;

@Stateless
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Usuario> getUsuarios() {
		return entityManager.createQuery("select distinct(a) from Usuario a join fetch a.telefones t", Usuario.class)
				.getResultList();
	}

	public Usuario atualizar(Usuario usuario) {
		return entityManager.merge(usuario);
	}

	public void salvar(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public void deletar(Usuario usuario) {
		entityManager.remove(usuario);
	}

	public Usuario getUsuarioPorId(Long id) {
		return entityManager.createQuery("select distinct(a) from Usuario a join fetch a.telefones t where a.id =:id",
				Usuario.class).setParameter("id", id).getSingleResult();
	}

	public List<Usuario> listarUsuarioPorNome(String nome) {
		return entityManager.createQuery(
				"select distinct(a) from Usuario a join fetch a.telefones t where a.nome LIKE CONCAT('%',:nome,'%')",
				Usuario.class).setParameter("nome", nome).getResultList();
	}

	public List<Usuario> listUsuarioPorEmail(String email) {
		 TypedQuery<Usuario> query = entityManager
				.createQuery("select distinct(a) from Usuario a join fetch a.telefones t where a.email =:email",
						Usuario.class)
				.setParameter("email", email);
		 List<Usuario> resultList = query.getResultList();
		return  resultList;
	}
}
