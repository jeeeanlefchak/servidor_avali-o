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
import br.fadep.pos.bean.FuncionarioBeanLocal;
import br.fadep.pos.model.DadosGrafico;
import br.fadep.pos.model.Funcionario;
import br.fadep.pos.model.HistoricoAvaliacao;

@Stateless
@Path("funcionario")
public class FuncionarioWS extends AbstractWs<Funcionario> {
	
	@EJB
	private FuncionarioBeanLocal funcionarioBean;

	@Override
	public AbstractBean<Funcionario> getDAS() {
		return funcionarioBean;
	}

	@GET
	@Path("funcionarios/{idempresa}")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response buscarFuncionarios(@PathParam("idempresa")Long idEmpresa){
		try {
			List<Funcionario> lista = funcionarioBean.buscaFuncionarios(idEmpresa);
			return Response.ok(lista).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}
	
	@GET
	@Path("codounome/{nome}/{idempresa}")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response buscarFuncionarios(@PathParam("nome")String codNome, @PathParam("idempresa")Long idEmpresa){
		try {
			List<Funcionario> lista = funcionarioBean.buscaCodOuNome(codNome, idEmpresa);
			return Response.ok(lista).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}
	
	@POST
	@Path("grafico")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response buscarDadosGrafico(DadosGrafico dados){
		try {
			DadosGrafico data =  funcionarioBean.grafico(dados);
			return Response.ok(data).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}
	
	@GET
	@Path("testeconexao")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response testeConexao(){
		try {
			return Response.ok(true).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}
	
	@POST
	@Path("historico")
	@Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	public Response historicoNotas(DadosGrafico dados){
		try {
			List<HistoricoAvaliacao> data =  funcionarioBean.historicoDeAvaliacoes(dados);
			return Response.ok(data).build(); 
		} catch (Exception e) {
			return Response.ok(e).build();
		}
	}
}
