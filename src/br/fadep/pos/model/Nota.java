package br.fadep.pos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.fadep.pos.model.generic.AbstractEntityImpl;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;
import javax.transaction.Transactional;

@SqlResultSetMapping(name="dadosgraficonota",
classes={@ConstructorResult(targetClass=DadosGrafico.class, columns={
		@ColumnResult(name="dataInicio",type=Date.class),
		@ColumnResult(name="dataFinal",type=Date.class),
		@ColumnResult(name="idVendedor",type=Long.class),
		@ColumnResult(name="nota1",type=Double.class),
		@ColumnResult(name="nota2",type=Double.class),
		@ColumnResult(name="nota3",type=Double.class),
		@ColumnResult(name="nota4",type=Double.class)
})})


@Entity
public class Nota extends AbstractEntityImpl{

	@Column
	private Double nota;
	@Column
	private Long idFuncionario;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFuncionario", referencedColumnName="id",insertable=false, updatable=false)
	private Funcionario funcionario;
	@Column(length = 100)
	private String numeroNota;
	
	public Nota(Date dataInicio, Date dataFinal, Long idVendedor, Double nota1, Double nota2,Double nota3,Double nota4) {
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.idVendedor = idVendedor;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.nota4 = nota4;
	}
	public Nota() {
		// TODO Auto-generated constructor stub
	}
	
	@Transient
	private Date dataInicio;
	@Transient
	private Date dataFinal;
	@Transient
	private Long idVendedor;
	@Transient
	private Double nota1;
	@Transient
	private Double nota2;
	@Transient
	private Double nota3;
	@Transient
	private Double nota4;
	
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Long getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
	public Double getNota1() {
		return nota1;
	}
	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}
	public Double getNota2() {
		return nota2;
	}
	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}
	public Double getNota3() {
		return nota3;
	}
	public void setNota3(Double nota3) {
		this.nota3 = nota3;
	}
	public Double getNota4() {
		return nota4;
	}
	public void setNota4(Double nota4) {
		this.nota4 = nota4;
	}
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	
}
