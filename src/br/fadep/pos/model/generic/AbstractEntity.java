package br.fadep.pos.model.generic;

import java.io.Serializable;
import java.util.Date;

public interface AbstractEntity extends Serializable  {

	public Long getId();
	public void setId(Long id);
	public Date getDataCadastro();
	public abstract void setDataCadastro(Date dataCadastro);
	public boolean isNew();
	public abstract Boolean getDeletado();
	public abstract void setDeletado(Boolean deletado);
	public abstract Date getDataAlteracao();
    public abstract void setDataAlteracao(Date dataAlteracao);
    public abstract Long getVersao();
    public abstract void setVersao(Long versao);
}