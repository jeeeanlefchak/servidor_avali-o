package br.fadep.pos.bean;

import java.util.List;

import javax.ejb.Local;

import br.fadep.pos.model.ConfiguracaoNovo;
import br.fadep.pos.model.Empresa;
import br.fadep.pos.model.Funcionario;

@Local
public interface EmpresaBeanLocal extends AbstractBean<Empresa>{
	
	public List<Empresa> buscaEmpresa()throws Exception;
	
	public Empresa logar(Empresa empresa)throws Exception;
	
}
