package br.fadep.pos.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.fadep.pos.model.DadosGrafico;
import br.fadep.pos.model.Empresa;
import br.fadep.pos.model.Funcionario;

@Stateless
public class FuncionarioBean extends AbstractBeanImpl<Funcionario> implements FuncionarioBeanLocal{

	@Override
	public Class<Funcionario> getClasses() throws Exception {
		return Funcionario.class;
	}
	@EJB
	private EmpresaBeanLocal empresaBean;
	
	public List<Funcionario> buscaFuncionarios(Long idEmpresa) throws Exception{
			String sql = "SELECT id, nome, codigo, idempresa, dataCadastro "
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
						sql += "inner join funcionario f on f.id = n1.idfuncionario and  f.idEmpresa = :idEmpresa ";
					}
					sql +=  "where n1.nota = 1 and n1.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n1.idFuncionario = :idFuncionario ";
			}
			
			sql += ") as  nota1,";
			sql += " (select count(n2.id) from nota n2 ";
					if(dados.getIdEmpresa() != null){
						sql += "inner join funcionario f on f.id = n2.idfuncionario and  f.idEmpresa = :idEmpresa  ";
					}
					sql += " where n2.nota = 2 and n2.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n2.idFuncionario = :idFuncionario ";
			}
			sql += ") as  nota2,";
			sql += " (select count(n3.id) from nota n3 ";
			if(dados.getIdEmpresa() != null){
				sql += "inner join funcionario f on f.id = n3.idfuncionario and  f.idEmpresa = :idEmpresa ";
			}
					sql += " where n3.nota = 3 and n3.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n3.idFuncionario = :idFuncionario ";
			}
			sql += ") as  nota3, ";
			sql += " (select count(n4.id) from nota n4 ";
				if(dados.getIdEmpresa() != null){
					sql += "inner join funcionario f on f.id = n4.idfuncionario and  f.idEmpresa = :idEmpresa ";
				}
			 sql += "where n4.nota = 4  and n4.datacadastro between :dataInicio and :dataFim ";
			if(dados.getIdVendedor() != null){
				sql += "and n4.idFuncionario = :idFuncionario ";
			}
			sql += ") as  nota4, null as datainicio, null as dataFinal, null as idVendedor ";
		Query query = entity.createNativeQuery(sql, "dadosgraficonota");
		if(dados.getIdVendedor() != null){
			query.setParameter("idFuncionario", dados.getIdVendedor());
		}
		if(dados.getIdEmpresa() != null){
			query.setParameter("idEmpresa", dados.getIdEmpresa());
		}
		query.setParameter("dataInicio", dados.getDataInicio());
		query.setParameter("dataFim", dados.getDataFinal());
		return (DadosGrafico) query.getResultList().get(0);
	}
	
	@Override
	public Funcionario salvar(Funcionario obj) throws Exception {
		if(obj.getIdEmpresa() != null){
			Empresa empresa = empresaBean.find(obj.getIdEmpresa());
			obj.setEmpresa(empresa);
		}
		return super.salvar(obj);
	}
}
