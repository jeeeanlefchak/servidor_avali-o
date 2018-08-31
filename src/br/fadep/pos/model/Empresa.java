package br.fadep.pos.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;

import br.fadep.pos.model.generic.AbstractEntityImpl;
import javax.persistence.SqlResultSetMapping;

//@SqlResultSetMapping(name="resultMarca",
//classes={@ConstructorResult(targetClass=Empresa.class, columns={
//		@ColumnResult(name="id",type=Long.class),
//		@ColumnResult(name="nome",type=String.class),
//		
//})})


@Entity
public class Empresa extends AbstractEntityImpl{

	@Column(length = 200)
	private String nome;
	@Column(columnDefinition = " text")
	private String logo;
	@Column(length = 200)
	private String login;
	@Column(length = 200)
	private String senha;
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	 public Empresa(Long id, String nome) {
		this.setId(id);
		this.nome = nome;
	}
	 public Empresa() {
		// TODO Auto-generated constructor stub
	}
	
}
