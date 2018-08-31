package br.fadep.pos.bean;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.fadep.pos.model.generic.AbstractEntity;

public abstract class AbstractBeanImpl <T extends AbstractEntity>  implements AbstractBean<T>{

	@PersistenceContext(unitName="mobile")
	protected EntityManager entity;

	
    protected EntityManager entityManager;
	
	@Override
	public T salvar(T obj) throws Exception {
		if(obj.isNew()){
			return inserir(obj);
		}else{
			return alterar(obj);
		}
	}

	@Override
	public T inserir(T obj) throws Exception {
		entity.persist(obj);
		entity.flush();
		return obj;
	}

	@Override
	public T alterar(T obj) throws Exception {
		T objOld = find(obj.getId());
		 obj.setDataAlteracao(new Date());
	     obj = entity.merge(obj);
	     entity.flush();
	     return objOld;
	}

	@Override
	public Boolean remover(Long id) throws Exception {
		T obj = find(id);
		entity.remove(obj);
		entity.flush();
		return true;
	}

	@Override
	public T find(Long id) throws Exception {
		T obj = entity.find(getClasses(), id);
		return obj;
	}
	
    @Override
    public List<T> findAll() throws Exception {         
        try { 
            CriteriaQuery cq = entity.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getClasses()));
            Query q = entity.createQuery(cq);            
            return q.getResultList();
        } finally {
        }
    }
		
}
