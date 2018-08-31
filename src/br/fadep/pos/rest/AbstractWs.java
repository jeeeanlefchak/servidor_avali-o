package br.fadep.pos.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.fadep.pos.bean.AbstractBean;
import br.fadep.pos.model.generic.AbstractEntity;

public abstract class AbstractWs <T extends AbstractEntity>{
	
	public abstract AbstractBean<T> getDAS();
	
	 	@Path("salvar")
	    @POST 
//	    @Consumes({MediaType.APPLICATION_JSON +";charset=utf-8"})
	    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	    public Response salvar( T entity) throws Exception { 
	    	Response res = null;
	    	try{
//	    		res = Response.ok(toJson(getDAS().save(getUsuario(headers), entity))).build();
	    		T obj= getDAS().salvar(entity); 
	            return Response.ok(obj).build();
	        }catch (Exception e) {
	        	e.getMessage();
	        	res  = Response.serverError().entity(e.getLocalizedMessage()).build();
			}
	        return res;
	    }
	 	
	 	 @GET
	     @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	     public Response findAll(@Context HttpHeaders headers) throws Exception {
	     	List<T> json = getDAS().findAll(); 
	         return Response.ok(json).build();
	     }

	     @PUT
	     @Path("{id}")
	     @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	     public Response edit(@PathParam("id") Long id, T entity) throws Exception {
	     	T json = getDAS().alterar(entity); 
	         return Response.ok(json).build();
	     }
	
}
