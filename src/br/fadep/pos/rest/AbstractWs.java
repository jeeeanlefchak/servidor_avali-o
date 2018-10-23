package br.fadep.pos.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import javax.ws.rs.core.Response.Status;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.fadep.pos.bean.AbstractBean;
import br.fadep.pos.bean.hiber.HibernateProxyAdapter;
import br.fadep.pos.model.exeptions.RegraException;
import br.fadep.pos.model.generic.AbstractEntity;

public abstract class AbstractWs <T extends AbstractEntity>{
	private Gson gson;
    private final String FORMATO_DATA = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private String[] dependeciasIgnorar = new String[0];
    
	public abstract AbstractBean<T> getDAS();
	
	 	@Path("salvar")
	    @POST 
//	    @Consumes({MediaType.APPLICATION_JSON +";charset=utf-8"})
	    @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	    public Response salvar( T entity) throws Exception { 
	    	Response res = null;
	    	try{
//	    		res = Response.ok(toJson(getDAS().save(getUsuario(headers), entity))).build();
//	    		T obj= getDAS().salvar(entity);
	    		String obj = toJson(getDAS().salvar(entity));
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
	 		 try {
	 			List<T> json = getDAS().findAll(); 
		         return Response.ok(json).build();
			} catch (Exception e) {
				return sendException(e);
			}
	     	
	     }

	     @PUT
	     @Path("{id}")
	     @Produces({MediaType.APPLICATION_JSON +";charset=utf-8"})
	     public Response edit(@PathParam("id") Long id, T entity) throws Exception {
	     	try {
	     		 T json = getDAS().alterar(entity); 
		         return Response.ok(json).build();
			} catch (Exception e) {
				return sendException(e);
			}
	     }
	     
	     @GET
	     @Path("{id}")
	     @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
	     public Response find(@PathParam("id") Long id) throws Exception {
	         try {
	 	    	String obj = toJson(getDAS().find(id));
	 	        return Response.ok(obj).build();
	         }catch (Exception e) {
	 			return sendException(e);
	 		}
	     }
	
	     public Response sendException(Exception mensagem) {
	     	if (mensagem instanceof RegraException) {
	     		RegraException exc = (RegraException)mensagem;
	     		if (exc.getCodErro() == 1 || exc.getCodErro() == 2 || exc.getCodErro() == 13) {
	     			Response.status(Status.UNAUTHORIZED).entity(mensagem).build();
	     		}    		 
	     	}
	         return Response.status(Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
	     }
	     
	     public GsonBuilder getGsonBuilder() {
	         TypeAdapter<Calendar> calendarAdapter = new TypeAdapter<Calendar>() {

	             @Override
	             public void write(JsonWriter arg0, Calendar data) throws IOException {
	                 if (data != null) {
	                     SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
	                     arg0.value(sdf.format(data.getTime()));
	                 } else {
	                     arg0.nullValue();
	                 }
	             }

	             @Override
	             public Calendar read(JsonReader arg0) throws IOException {
	                 String valueDate = arg0.nextString();
	                 if (valueDate != null) {
	                     try {
	                         SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
	                         Date data = sdf.parse(valueDate);
	                         GregorianCalendar calendario = new GregorianCalendar();
	                         calendario.setTime(data);
	                         return calendario;
	                     } catch (ParseException e) {
	                         try {
	                             SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
	                             Date data = sdf.parse(valueDate);
	                             GregorianCalendar calendario = new GregorianCalendar();
	                             calendario.setTime(data);
	                             return calendario;
	                         } catch (Exception ex) {
	                             ex.printStackTrace();
	                         }
	                         e.printStackTrace();
	                         return null;
	                     }
	                 }
	                 return null;
	             }
	         };

	         TypeAdapter<Date> calendarDate = new TypeAdapter<Date>() {

	             @Override
	             public void write(JsonWriter arg0, Date data) throws IOException {
	                 if (data != null) {
	                     SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
	                     arg0.value(sdf.format(data));
	                 } else {
	                     arg0.nullValue();
	                 }
	             }

	             @Override
	             public Date read(JsonReader arg0) throws IOException {
	                 String valueDate = arg0.nextString();
	                 if (valueDate != null) {
	                     try {
	                         SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
	                         Date data = sdf.parse(valueDate);
	                         return data;
	                     } catch (ParseException e) {
	                         try {
	                             SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA);
	                             Date data = sdf.parse(valueDate);
	                             return data;
	                         } catch (Exception ex) {
	                             ex.printStackTrace();
	                         }
	                         e.printStackTrace();
	                         return null;
	                     }
	                 }
	                 return null;
	             }
	         };
	         GsonBuilder builder = new GsonBuilder();
	         builder.serializeNulls();
	         builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	         builder.registerTypeAdapter(GregorianCalendar.class, calendarAdapter);
	         builder.registerTypeAdapter(Calendar.class, calendarAdapter);
	         builder.registerTypeAdapter(Date.class, calendarDate);
	         builder.enableComplexMapKeySerialization();
	         builder.registerTypeAdapterFactory(HibernateProxyAdapter.FACTORY);
	         builder.setPrettyPrinting();
	         builder.setExclusionStrategies(new ExclusionStrategy() {
	             public boolean shouldSkipField(FieldAttributes f) {
	                 for (String dep : dependeciasIgnorar) {
	                     if (f.getName().equals(dep)) {
	                         return true;
	                     }
	                 }
	                 return false;
	             }

	             public boolean shouldSkipClass(Class<?> clazz) {
	                 return false;
	             }
	         });

	         return builder;
	     }

	     public Gson getGson() {
	         return getGsonBuilder().create();
	     }

	     public String toJson(Object obj) {
	         return getGson().toJson(obj);
	     }
}
