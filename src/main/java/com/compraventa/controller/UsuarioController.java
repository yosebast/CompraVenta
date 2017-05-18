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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.compraventa.entidades.Usuarios;
import com.compraventa.service.UsuarioManager;

@Controller
public class UsuarioController {
	
	protected static Logger logger = Logger.getLogger("UsuarioController");

	@Autowired
	UsuarioManager personal;
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/showProducts", method = RequestMethod.GET)
	public String muestraEntrada(Model model) {

		model.addAttribute("usuario", new Usuarios());

		return "showMessage";
	}

	@RequestMapping(value = "/addUsuario", method = RequestMethod.POST)
	public String addUsuario(@ModelAttribute("usuario") @Validated Usuarios usuario, BindingResult result,
			Model model) {
		
		boolean insertando = WebUtils.hasSubmitParameter(request, "insertar");
		
		if(insertando){
		if (result.hasErrors()) {

			return "showMessage";
		}

		personal.insertUsuario(usuario);

		}
			model.addAttribute("usuario", new Usuarios());
			return "showMessage";
		
	}

	@RequestMapping(value = "cargaUsuarios", method = RequestMethod.GET)
	public String loadUsuarios(Model model) {

		List<Usuarios> listaUsus = personal.cargaUsuariosServicio();

		model.addAttribute("listaUsuarios", listaUsus);

		for (Usuarios usua : listaUsus) {

			String nombre = usua.getNombre();

			if (nombre.equals("Sebastian")) {
				// recogemos solo los nombres

				System.out.println("prefecto");

			}

		}

		// model.addAttribute("usuario", new Usuarios());

		// return "showMessage";

		return "Usuarios/ListadoUsuarios";

	}
	
		
	@RequestMapping(value="ruta", method=RequestMethod.GET)
	public String upDateUsuario(@RequestParam("idUsuario") Integer idUsuario, Model model){
		
		Usuarios usu = null; 
		
		if(idUsuario != null){			
			 usu =  personal.getById(idUsuario);		
		}
		
		if(usu != null){			
			model.addAttribute("usuario", usu);		
			
			return "Usuarios/actualizaUsuarios";
			
		}
		
		return "Usuarios/ListadoUsuarios";
		
	}
	
	@RequestMapping(value="upDateUsuario", method= RequestMethod.POST)
	public String ejecutaUpdate(@ModelAttribute("usuario") @Validated Usuarios usua, BindingResult result, Model modelo){
		
		
		boolean  seleccion = WebUtils.hasSubmitParameter(request, "actualizar");
		
		if(seleccion){
			
			
			if (result.hasErrors()) {
				
				return "Usuarios/actualizaUsuarios";	
			}
			Integer usuId = new Integer(usua.getIdusuario());
				personal.updateUsuario(usua, usuId);				
				return "redirect:/cargaUsuarios";	
				
			}else{				
						
				return "redirect:/cargaUsuarios";			
		
			}
	}
	
	@RequestMapping(value="delete/{idusuario}", method = RequestMethod.GET)
	public String borrar(@PathVariable("idusuario") Integer idusuario){
		
		
		personal.deleteUsuario(idusuario);		
		
		return "redirect:/cargaUsuarios";
		
	}
	
	
	
	//con los siguientes metodos recuperamos objetos y listados de objetos
	
	@RequestMapping(value="/usuarios", method=RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody List<Usuarios> getAllUsuarios(){
		
		logger.debug("provider has received request to get all persons");
		
		List<Usuarios> listadoUsuarios = personal.cargaUsuariosServicio();
		
		return listadoUsuarios;
		
		
	}

	
	

}




