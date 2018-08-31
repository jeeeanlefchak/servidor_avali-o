package br.fadep.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.fadep.pos.model.generic.AbstractEntityImpl;


@Entity
public class ConfiguracaoNovo extends AbstractEntityImpl{

	@Column(length=300)
	private String nome;
	@Column(length=50)
	private String cnpj;
	
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
