package br.fadep.pos.model;

import java.util.Date;

public class HistoricoAvaliacao {
	public String funcionario;
	public String avaliacao;
	public String numeronota;
	public Date dataCadastro;
	
	public HistoricoAvaliacao(String funcionario, String avaliacao, String numeronota , Date dataCadastro) {
		this.funcionario = funcionario;
		this.avaliacao = avaliacao;
		this.numeronota = numeronota;
		this.dataCadastro = dataCadastro;
	}
	
	public HistoricoAvaliacao() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String getNumeronota() {
		return numeronota;
	}
	public void setNumeronota(String numeronota) {
		this.numeronota = numeronota;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
