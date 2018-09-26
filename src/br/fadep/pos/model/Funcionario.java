package br.fadep.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;

import javax.persistence.*;
import br.fadep.pos.model.generic.AbstractEntityImpl;

@SqlResultSetMappings({
	@SqlResultSetMapping(name="funcionario",
	classes={@ConstructorResult(targetClass=Funcionario.class, columns={
			@ColumnResult(name="id",type=Long.class),
			@ColumnResult(name="nome",type=String.class),
			@ColumnResult(name="codigo",type=String.class),
			@ColumnResult(name="idempresa",type=Long.class),
			@ColumnResult(name="dataCadastro",type=Date.class),
			@ColumnResult(name="versao", type=Long.class)
	})}),
	@SqlResultSetMapping(name="HistoricoAvaliacao",
	classes={@ConstructorResult(targetClass=HistoricoAvaliacao.class, columns={
			@ColumnResult(name="funcionario",type=String.class),
			@ColumnResult(name="avaliacao",type=String.class),
			@ColumnResult(name="numeronota",type=String.class),
			@ColumnResult(name="dataCadastro",type=Date.class)
	})})

})

@Entity
public class Funcionario extends AbstractEntityImpl {
	
	@Column(length = 200)
	private String nome;
	@Column(length = 25)
	private String codigo;
	@Column
	private Long idEmpresa;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idEmpresa", referencedColumnName="id",insertable=false, updatable=false)
	private Empresa empresa;
	
	
	public Funcionario(Long id, String nome, String codigo, Long idEmpresa, Date dataCadastro, Long versao) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.idEmpresa = idEmpresa;
		this.dataCadastro = dataCadastro;
		this.versao = versao;
	}
	Funcionario(){
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
