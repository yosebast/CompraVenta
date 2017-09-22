package com.compraventa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
	
	
// 1.-la carga del listado
	
	@RequestMapping(value="/listSection", method=RequestMethod.GET)
	public String listadoSection(Model model){
		
		List<Seccion> listado = secman.getAll();		
		model.addAttribute("listSection", listado);
		
		return "Seccion/ListadoSeccion";
		
	}
	
	//2.-agregar una seccion
	@RequestMapping(value="/addSeccion", method=RequestMethod.GET)
	public String NewSeccion(Model model){
		
		model.addAttribute("seccion", new Seccion());
		model.addAttribute("edit", false);
		
		return "Seccion/actualizaSeccion";		
	}	
	
	//3.+-insertar una seccion
	@RequestMapping(value="/addSeccion", method=RequestMethod.POST)
	public String addSeccion(@ModelAttribute("seccion") @Valid Seccion seccion, BindingResult result, Model modelo){
		
		if(result.hasErrors()){			
			return  "Seccion/actualizaSeccion";			
		}		
		
		secman.persist(seccion);
		//regresa al listado de secciones
		return "redirect:/listSection";
	}
	
	//4.- delete seccion
	
	@RequestMapping(value="/delete-section-{idseccion}", method=RequestMethod.GET)
	public String deleteSeccion(@PathVariable("idseccion") Integer idseccion){
		
		Seccion sec = secman.get(idseccion);
		
		secman.remove(sec);
		
		return "redirect:/listSection";
	}
	
	
	//5.- ir a la pagina de actualizar
	
	@RequestMapping(value="/edit-section-{idseccion}", method=RequestMethod.GET)
	public String irPagActualizaSecion(@PathVariable("idseccion") Integer idseccion,  Model model){
		
		Seccion seccion = secman.get(idseccion);
		
		model.addAttribute("seccion", seccion);
		model.addAttribute("edit", true);
		
		return "Seccion/actualizaSeccion";
	}
	
	
	
	//6.- actualizar seccion
	
	@RequestMapping(value="/edit-section-{idseccion}", method=RequestMethod.POST)	
	public String actualizaSeccion(@ModelAttribute("seccion") @Valid Seccion seccion, BindingResult result, @PathVariable("idseccion") Integer idseccion){			
		
		secman.update(seccion);		
		
		return "redirect:listSection";
	}
	
	
	/*<input type="button" id="seccion" name="seccion" value="seccion"  onclick="location.href='<c:url value="cargaSeccion"/>'"/> 
	 * si vienes de un boton va directmente insertSeccion   sin la barra /
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
		
	}*/
	
	
	//con los siguientes metodos recuperamos objetos y listados de objetos
	
	@RequestMapping(value="seccions", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<Seccion> getAllSeccion(){	
		
		logger.debug("Provider has received request to get all seccions");	
		
		List<Seccion> listaSeccion = secman.getAll();
		
		return listaSeccion;
		
	}
	
	
	@RequestMapping(value="/seccion/{idseccion}", method=RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody Seccion getIdSeccion(@PathVariable("idseccion") int idseccion){
		
		logger.debug("provider has received request to get one seccions");
		
		Seccion seccion = secman.get(idseccion);
		
		return seccion;
	}
	
	
	
	
	

}
