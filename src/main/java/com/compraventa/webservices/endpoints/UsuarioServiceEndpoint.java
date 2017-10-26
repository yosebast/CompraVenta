package com.compraventa.webservices.endpoints;


import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.compraventa.entidades.Usuarios;
import com.compraventa.service.UsuarioManager;




//@Endpoint
public class UsuarioServiceEndpoint {
	
/*	private static final String TARGET_NAMESPACE = "http://com/compraventa/webservices/createserviceusuario";
	
	@Autowired
	private UsuarioManager usuario;
	
	 @PayloadRoot(localPart = "UsuariosDetailsRequest",   namespace = TARGET_NAMESPACE)  
	public @ResponsePayload UsuariosDetailsResponse getUsuario(@RequestPayload UsuariosDetailsRequest request){
		
		 System.out.println("Entro al metodo");  
		UsuariosDetailsResponse response = new UsuariosDetailsResponse();
		
		Usuarios usu = usuario.get(request.getIdUser());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(usu.getReg_Date());
		
		User us = new User();
		
		us.setApellidos(usu.getApellidos());
		us.setClave(usu.getClave());	
		us.setEdad(usu.getEdad());
		us.setEmail(usu.getEmail());
		us.setFotoUrl(usu.getFotoUrl());
		us.setIdusuario(usu.getIdusuario());
		us.setNombre(usu.getNombre());
		try {
			us.setRegDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		us.setRegistrado(usu.getRegistrado());
		us.setClave(usu.getClave());
		
		
		
		// response.setUsuarioDetails(us);
		 
		 System.out.println("salgo del metodo");  
		 return response;
		
		
		 
		
		
	}*/
	
	

}
