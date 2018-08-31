package br.fadep.pos.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.fadep.pos.bean.AbstractBean;
import br.fadep.pos.bean.EmpresaBeanLocal;
import br.fadep.pos.bean.NotaBeanLocal;
import br.fadep.pos.model.Empresa;
import br.fadep.pos.model.Funcionario;
import br.fadep.pos.model.Nota;

@Stateless
@Path("nota")
public class NotaWs extends AbstractWs<Nota>{
	
	@EJB
	private NotaBeanLocal notaBean;

	@Override
	public AbstractBean<Nota> getDAS() {
		return notaBean;
	}
	
	

}
