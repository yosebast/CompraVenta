package com.compraventa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;


import com.compraventa.entidades.Seccion;
import com.compraventa.service.SeccionManager;


@Controller
public class SeccionController {
	
	Logger logger = Logger.getLogger("SeccionController");
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SeccionManager secman;
	
	@RequestMapping(value="cargaSeccion", method = RequestMethod.GET)
	public String cargaFormInsertaSeccion(Model model){
		
		model.addAttribute("seccion", new Seccion());
		
		
		return "Seccion/insertaSeccion";
	}
		
	@RequestMapping(value="insertSeccion", method = RequestMethod.POST)
	public String insertaSeccion(@ModelAttribute("seccion") @Validated Seccion seccion, BindingResult result, Model model){
		
		boolean insertando = WebUtils.hasSubmitParameter(request, "insertar");
		
		if(insertando){
			
			if(result.hasErrors()){
				
				return "Seccion/insertaSeccion";
				
			}
			
			// insertamos la seccion
			
			secman.insertaSeccion(seccion);
			
			return "redirect:/cargaUsuarios";	
			
		}else{
			
			return "redirect:/cargaUsuarios";	
		}
		
	}
	
	
	//con los siguientes metodos recuperamos objetos y listados de objetos
	
	@RequestMapping(value="seccions", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<Seccion> getAllSeccion(){	
		
		logger.debug("Provider has received request to get all seccions");	
		
		List<Seccion> listaSeccion = secman.cargaSeccionServicio();
		
		return listaSeccion;
		
	}
	
	
	@RequestMapping(value="/seccion/{idseccion}", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody Seccion getIdSeccion(@PathVariable("idseccion") int idseccion){
		
		logger.debug("provider has received request to get one seccions");
		
		Seccion seccion = secman.cargaSeccionById(idseccion);
		
		return seccion;
	}
	
	
	
	
	

}
