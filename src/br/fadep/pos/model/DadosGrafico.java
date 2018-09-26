package br.fadep.pos.model;

import java.util.Date;
import javax.persistence.*;

public class DadosGrafico {
	public Date dataInicio;
	public Date dataFinal;
	public Long idVendedor;
	public Double nota1;
	public Double nota2;
	public Double nota3;
	public Double nota4;
	public Long idEmpresa;
	public String numeroNota;
	
	public DadosGrafico(Date dataInicio, Date dataFinal, Long idVendedor, Double nota1, Double nota2,Double nota3,Double nota4) {
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.idVendedor = idVendedor;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.nota4 = nota4;
	}
	public DadosGrafico() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
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
}
