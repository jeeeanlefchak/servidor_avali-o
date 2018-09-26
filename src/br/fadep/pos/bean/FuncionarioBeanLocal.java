package br.fadep.pos.bean;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.PathParam;

import br.fadep.pos.model.DadosGrafico;
import br.fadep.pos.model.Funcionario;
import br.fadep.pos.model.HistoricoAvaliacao;

@Local
public interface FuncionarioBeanLocal extends AbstractBean<Funcionario> {
	
	public List<Funcionario> buscaFuncionarios(Long idEmpresa) throws Exception;
	
	public DadosGrafico grafico(DadosGrafico dados)throws Exception;
	
	public List<Funcionario> buscaCodOuNome(String codNome, Long idEmpresa) throws Exception;
	
	public List<HistoricoAvaliacao> historicoDeAvaliacoes(DadosGrafico dadosGafico)throws Exception;
}
