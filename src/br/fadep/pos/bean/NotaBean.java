package br.fadep.pos.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fadep.pos.model.Empresa;
import br.fadep.pos.model.Nota;

@Stateless
public class NotaBean extends AbstractBeanImpl<Nota> implements NotaBeanLocal{

	@Override
	public Class<Nota> getClasses() throws Exception {
		// TODO Auto-generated method stub
		return Nota.class;
	}
	
	

}
