package com.compraventa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.compraventa.entidades.Seccion;
import com.compraventa.service.SeccionManager;


@Controller
public class SeccionController {
	
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
	
	
	
	
	
	
	
	

}
