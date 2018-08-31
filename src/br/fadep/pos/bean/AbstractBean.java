package br.fadep.pos.bean;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import br.fadep.pos.model.generic.AbstractEntity;

public interface AbstractBean<t extends AbstractEntity> {

	public t salvar(t obj)throws Exception;
	
	public t inserir(t obj)throws Exception;
	
	public t alterar(t obj)throws Exception;
	
	public Boolean remover(Long id)throws Exception;
	
	public t find(Long id)throws Exception;
	
	public Class<t> getClasses() throws Exception;

	public List<t> findAll()throws Exception;


}
