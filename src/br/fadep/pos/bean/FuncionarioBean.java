package br.fadep.pos.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fadep.pos.model.DadosGrafico;
import br.fadep.pos.model.Empresa;
import br.fadep.pos.model.Funcionario;
import br.fadep.pos.model.HistoricoAvaliacao;

@Stateless
public class FuncionarioBean extends AbstractBeanImpl<Funcionario> implements FuncionarioBeanLocal{

	@Override
	public Class<Funcionario> getClasses() throws Exception {
		return Funcionario.class;
	}
	@EJB
	private EmpresaBeanLocal empresaBean;
	
	public List<Funcionario> buscaFuncionarios(Long idEmpresa) throws Exception{
			String sql = "SELECT id, nome, codigo, idempresa, dataCadastro, versao "
					+ "FROM funcionario where deletado = false and idempresa = :idempresa"
					+ " order by datacadastro desc";
			Query query = entity.createNativeQuery(sql, "funcionario");
			query.setParameter("idempresa", idEmpresa);
			return query.getResultList();
	}
	
	public DadosGrafico grafico(DadosGrafico dados)throws Exception{
		String sql = "select ";
			sql += " (select  count(n1.id) from nota n1 ";
					if(dados.getIdEmpresa() != null){
						sql += "inner join funcionario f on f.id = n1.idfuncionario and  f.idEmpresa = :idEmpresa and f.deletado = false ";
					}
					sql +=  "where n1.nota = 1 and n1.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n1.idFuncionario = :idFuncionario ";
			}
			if(dados.getNumeroNota().length() > 0) {
				sql += " and n1.numeronota ilike :numeronota ";
			}
			sql += ") as  nota1,";
			sql += " (select count(n2.id) from nota n2 ";
					if(dados.getIdEmpresa() != null){
						sql += "inner join funcionario f on f.id = n2.idfuncionario and  f.idEmpresa = :idEmpresa  and f.deletado = false ";
					}
					sql += " where n2.nota = 2 and n2.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n2.idFuncionario = :idFuncionario ";
			}
			if(dados.getNumeroNota().length() > 0) {
				sql += " and n2.numeronota ilike :numeronota ";
			}
			sql += ") as  nota2,";
			sql += " (select count(n3.id) from nota n3 ";
			if(dados.getIdEmpresa() != null){
				sql += "inner join funcionario f on f.id = n3.idfuncionario and  f.idEmpresa = :idEmpresa and f.deletado = false ";
			}
					sql += " where n3.nota = 3 and n3.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n3.idFuncionario = :idFuncionario ";
			}
			if(dados.getNumeroNota().length() > 0) {
				sql += " and n3.numeronota = :numeronota ";
			}
			sql += ") as  nota3, ";
			sql += " (select count(n4.id) from nota n4 ";
				if(dados.getIdEmpresa() != null){
					sql += "inner join funcionario f on f.id = n4.idfuncionario and  f.idEmpresa = :idEmpresa and f.deletado = false ";
				}
			 sql += "where n4.nota = 4  and n4.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n4.idFuncionario = :idFuncionario ";
			}
			if(dados.getNumeroNota().length() > 0) {
				sql += " and n4.numeronota ilike :numeronota ";
			}
			sql += ") as  nota4, null as datainicio, null as dataFinal, null as idVendedor ";
		Query query = entity.createNativeQuery(sql, "dadosgraficonota");
		if(dados.getIdVendedor() != null){
			query.setParameter("idFuncionario", dados.getIdVendedor());
		}
		if(dados.getIdEmpresa() != null){
			query.setParameter("idEmpresa", dados.getIdEmpresa());
		}
		if(dados.getNumeroNota().length() > 0) {
			query.setParameter("numeronota","%" + dados.getNumeroNota().toString().trim() +"%");
		}
		query.setParameter("dataInicio", dados.getDataInicio());
		query.setParameter("dataFim", dados.getDataFinal());
		return (DadosGrafico) query.getResultList().get(0);
	}
	
	public List<Funcionario> buscaCodOuNome(String codNome, Long idEmpresa) throws Exception{
		String sql = "Select * from funcionario where idempresa = :idempresa and "
				+ " (codigo ilike :codnome or nome ilike :codnome) and deletado = false";
		Query query = entity.createNativeQuery(sql, "funcionario");
		query.setParameter("codnome", "%" + codNome.trim() + "%");
		query.setParameter("idempresa", idEmpresa);
		return query.getResultList();
	}
	
	@Override
	public Funcionario salvar(Funcionario obj) throws Exception {
		if(obj.getIdEmpresa() != null){
			Empresa empresa = empresaBean.find(obj.getIdEmpresa());
			obj.setEmpresa(empresa);
		}
		return super.salvar(obj);
	}
	
	public List<HistoricoAvaliacao> historicoDeAvaliacoes(DadosGrafico dadosGafico)throws Exception{
		String sql = " select concat(f.codigo, ' - ' , f.nome) as funcionario, "
				+ "case when n.nota = 1 then concat( 1 ,' - Satisfeito') "  
				+ "     when n.nota = 2 then concat( 2 ,' - Regular') "  
				+ "     when n.nota = 3 then concat( 3 ,' - Ruim') "  
				+ "     when n.nota = 4 then concat( 4 ,' - Péssimo') end as avaliacao, n.numeronota as numeronota, "
				+ " n.datacadastro as dataCadastro from funcionario f " 
				+ " inner join nota n on n.idfuncionario = f.id and f.deletado = false"
				+ " where f.id = :idfuncionario and n.datacadastro between :datainicio and :datafim ";
		Query query = entity.createNativeQuery(sql, "HistoricoAvaliacao");
		query.setParameter("idfuncionario", dadosGafico.getIdVendedor());
		query.setParameter("datainicio", dadosGafico.getDataInicio());
		query.setParameter("datafim", dadosGafico.getDataFinal());
		return  query.getResultList();
	}
}
