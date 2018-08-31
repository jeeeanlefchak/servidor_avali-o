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
import br.fadep.pos.model.Empresa;
import br.fadep.pos.model.Funcionario;

@Stateless
@Path("empresa")
public class EmpresaWs extends AbstractWs<Empresa>{
	
	@EJB
	private EmpresaBeanLocal empresaBean;

	@Override
	public AbstractBean<Empresa> getDAS() {
		return empresaBean;
	}
	
	@GET
	@Path("buscaempresa")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response buscarFuncionarios(){
		try {
			List<Empresa> lisa = empresaBean.buscaEmpresa();
			return Response.ok(lisa).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}
	
	@POST
	@Path("logar")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response logar(Empresa empresa){
		try {
			Empresa lisa = empresaBean.logar(empresa);
			return Response.ok(lisa).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}

}
