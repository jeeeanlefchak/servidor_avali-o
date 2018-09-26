package br.fadep.pos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fadep.pos.model.Empresa;

@Stateless
public class EmpresaBean extends AbstractBeanImpl<Empresa> implements EmpresaBeanLocal{

	@Override
	public Class<Empresa> getClasses() throws Exception {
		// TODO Auto-generated method stub
		return Empresa.class;
	}
	
	public List<Empresa> buscaEmpresa()throws Exception{
		String sql = "SELECT * FROM empresa where deletado = false ";
		Query query = entity.createNativeQuery(sql, Empresa.class);
		return query.getResultList();
	}
	
	public Empresa logar(Empresa empresa)throws Exception{
		String sql = "SELECT * FROM empresa where upper(login) ilike :login and upper(senha) ilike :senha and deletado = false ";
		Query query = entity.createNativeQuery(sql, Empresa.class);
		query.setParameter("login", empresa.getLogin());
		query.setParameter("senha", empresa.getSenha());
//		if(!query.getResultList().isEmpty()){
//			return null;
//		}
		
		return (Empresa) query.getSingleResult();
	}

}
